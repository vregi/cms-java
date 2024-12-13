package com.slezkipotekli.cms.controller;

import com.slezkipotekli.cms.dto.DepartmentStatsDTO;
import com.slezkipotekli.cms.dto.EmployeeProjectDTO;
import com.slezkipotekli.cms.entity.Employee;
import com.slezkipotekli.cms.faker.EmployeeFaker;
import com.slezkipotekli.cms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author vregi, 12/13/2024
 */
@RestController
@RequestMapping("api/v1/employee")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PostMapping("/faker")
    public List<Employee> createEmployeeByFaker() {
        List<Employee> employees = Stream
                .generate(EmployeeFaker::createFakeEmployee)
                .limit(60)
                .toList();
        return employeeService.createEmployeesByFaker(employees);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/projects-info")
    public List<EmployeeProjectDTO> getEmployeesWithProjectsAndDepartments() {
        log.info("Fetching employees with their projects and departments");
        return employeeService.getEmployeesWithProjectsAndDepartments();
    }

    @GetMapping("/department/{departmentId}/high-hours")
    public List<EmployeeProjectDTO> getEmployeesByDepartmentWithHighHours(
            @PathVariable Integer departmentId) {
        log.info("Fetching employees from department {} with high allocated hours", departmentId);
        return employeeService.getEmployeesByDepartmentWithHighHours(departmentId);
    }

    @GetMapping("/department-stats")
    public List<DepartmentStatsDTO> getDepartmentStatistics() {
        log.info("Fetching department statistics");
        return employeeService.getDepartmentStatistics();
    }
}
