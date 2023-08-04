package com.microservice.employeeservice.service.impl;

import com.microservice.employeeservice.dto.APIResponseDto;
import com.microservice.employeeservice.dto.DepartmentDto;
import com.microservice.employeeservice.dto.EmployeeDto;
import com.microservice.employeeservice.entity.Employee;
import com.microservice.employeeservice.repository.EmployeeRepository;
import com.microservice.employeeservice.service.APIClient;
import com.microservice.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    //private RestTemplate restTemplate;
    //private WebClient webClient;
    private APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee emp=new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
        Employee savedEmployee=employeeRepository.save(emp);
        EmployeeDto savedEmployeeDto=new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode()
        );
        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee=employeeRepository.findById(employeeId).get();
       /*
        ResponseEntity<DepartmentDto> responseEntity= restTemplate.getForEntity("http://localhost:8080/api/departments/getdepartment/"+employee.getDepartmentCode(),
                DepartmentDto.class);
       DepartmentDto departmentDto=responseEntity.getBody();
        */

        //DepartmentDto departmentDto= webClient.get().uri("http://localhost:8080/api/departments/getdepartment/"+employee.getDepartmentCode())
          //      .retrieve()
            //    .bodyToMono(DepartmentDto.class)
              //  .block();
        DepartmentDto departmentDto=apiClient.getDepartment(employee.getDepartmentCode());
        EmployeeDto employeeDto=new EmployeeDto(

                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );
        APIResponseDto responseDto=new APIResponseDto();
        responseDto.setEmployeeDto(employeeDto);
        responseDto.setDepartmentDto(departmentDto);
        return responseDto;
    }
}
