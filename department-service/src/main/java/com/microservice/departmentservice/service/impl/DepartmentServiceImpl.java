package com.microservice.departmentservice.service.impl;

import com.microservice.departmentservice.dto.DepartmentDto;
import com.microservice.departmentservice.entity.Department;
import com.microservice.departmentservice.repository.DepartmentRepository;
import com.microservice.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    @Override

    public DepartmentDto saveNewDepartment(DepartmentDto departmentDto) {
        //convert dto to jpa entity
        Department department=new Department(departmentDto.getId(),departmentDto.getDepartmentName(),departmentDto.getDepartmentDescription(),departmentDto
                .getDepartmentCode());
      Department savedDepartment=  departmentRepository.save(department);
      //now again convert jpa entity to dto
        DepartmentDto savedDepartmentDto=new DepartmentDto(savedDepartment.getId(),
                savedDepartment.getDepartmentName(), savedDepartment.getDepartmentDescription(), savedDepartment.getDepartmentCode());
        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department=departmentRepository.findByDepartmentCode(code);
        DepartmentDto departmentDto=new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }
}
