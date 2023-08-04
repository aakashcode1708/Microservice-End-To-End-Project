package com.microservice.departmentservice.service;

import com.microservice.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto saveNewDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String code);
}
