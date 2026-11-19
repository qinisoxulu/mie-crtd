package com.murendeni.crtd.repository;

import com.murendeni.crtd.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}
