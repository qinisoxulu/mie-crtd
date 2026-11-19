package com.murendeni.crtd.service.impl;

import com.murendeni.crtd.dto.DepartmentRequest;
import com.murendeni.crtd.dto.DepartmentResponse;
import com.murendeni.crtd.entity.Department;
import com.murendeni.crtd.exceptions.ResourceNotFoundException;
import com.murendeni.crtd.repository.DepartmentRepository;
import com.murendeni.crtd.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public DepartmentResponse create(DepartmentRequest request) {
        return toResponse(repository.save(new Department(request.name(), request.description())));
    }

    @Override
    public List<DepartmentResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public DepartmentResponse findById(Long id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + id)));
    }

    private DepartmentResponse toResponse(Department d) {
        return new DepartmentResponse(d.getId(), d.getName(), d.getDescription());
    }
}
