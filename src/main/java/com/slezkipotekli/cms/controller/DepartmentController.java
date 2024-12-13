package com.slezkipotekli.cms.controller;

import com.slezkipotekli.cms.entity.Department;
import com.slezkipotekli.cms.entity.Employee;
import com.slezkipotekli.cms.faker.DepartmentFaker;
import com.slezkipotekli.cms.service.DepartmentService;
import com.slezkipotekli.cms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author vregi, 12/13/2024
 */
@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    
    @Transactional(readOnly = true)
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Integer id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @PostMapping("/faker")
    public List<Department> createDepartmentsByFaker() {
        List<Employee> availableEmployees = new ArrayList<>(employeeService.getAllEmployees());
        List<Department> departments = new ArrayList<>();

        for (int i = 0; i < 15 && !availableEmployees.isEmpty(); i++) {
            Department department = DepartmentFaker.createFakeDepartment(availableEmployees);
            departments.add(department);
        }

        return departmentService.createDepartmentsByFaker(departments);
    }

    @Transactional
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Integer id, @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
    }
}
