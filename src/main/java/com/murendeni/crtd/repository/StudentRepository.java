package com.murendeni.crtd.repository;

import com.murendeni.crtd.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByDepartmentId(Long departmentId);
}
