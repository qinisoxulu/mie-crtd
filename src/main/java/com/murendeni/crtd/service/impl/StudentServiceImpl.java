package com.murendeni.crtd.service.impl;

import com.murendeni.crtd.dto.StudentRequest;
import com.murendeni.crtd.dto.StudentResponse;
import com.murendeni.crtd.entity.Department;
import com.murendeni.crtd.entity.Student;
import com.murendeni.crtd.exceptions.ResourceNotFoundException;
import com.murendeni.crtd.repository.DepartmentRepository;
import com.murendeni.crtd.repository.StudentRepository;
import com.murendeni.crtd.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository students;
    private final DepartmentRepository departments;

    public StudentServiceImpl(StudentRepository students, DepartmentRepository departments) {
        this.students = students;
        this.departments = departments;
    }

    @Override
    public StudentResponse create(StudentRequest request) {
        Student student = new Student();
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setEmail(request.email());
        student.setDepartment(department(request.departmentId()));
        return toResponse(students.save(student));
    }

    @Override
    public List<StudentResponse> findAll() {
        return students.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public StudentResponse findById(Long id) {
        return toResponse(students.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + id)));
    }

    @Override
    public List<StudentResponse> findByDepartment(Long departmentId) {
        department(departmentId);
        return students.findByDepartmentId(departmentId).stream().map(this::toResponse).toList();
    }

    private Department department(Long id) {
        return departments.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + id));
    }

    private StudentResponse toResponse(Student s) {
        Department d = s.getDepartment();
        return new StudentResponse(s.getId(), s.getFirstName(), s.getLastName(), s.getEmail(), d.getId(), d.getName());
    }
}
