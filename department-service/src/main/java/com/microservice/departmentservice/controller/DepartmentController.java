package com.microservice.departmentservice.controller;

import com.microservice.departmentservice.dto.DepartmentDto;
import com.microservice.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;
    @PostMapping("/save")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
     return new ResponseEntity<>(departmentService.saveNewDepartment(departmentDto), HttpStatus.CREATED);
    }
    @GetMapping("/getdepartment/{code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("code") String deartmentCode){
        return new ResponseEntity<>(departmentService.getDepartmentByCode(deartmentCode),HttpStatus.OK);
    }
}
