package com.murendeni.crtd.service;

import com.murendeni.crtd.dto.DepartmentRequest;
import com.murendeni.crtd.dto.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse create(DepartmentRequest request);

    List<DepartmentResponse> findAll();

    DepartmentResponse findById(Long id);
}
