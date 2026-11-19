package com.murendeni.crtd.service;

import com.murendeni.crtd.dto.StudentRequest;
import com.murendeni.crtd.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse create(StudentRequest request);

    List<StudentResponse> findAll();

    StudentResponse findById(Long id);

    List<StudentResponse> findByDepartment(Long departmentId);
}
