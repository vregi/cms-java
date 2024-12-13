package com.slezkipotekli.cms.service;

import com.slezkipotekli.cms.dto.DepartmentStatsDTO;
import com.slezkipotekli.cms.dto.EmployeeProjectDTO;
import com.slezkipotekli.cms.entity.Employee;
import com.slezkipotekli.cms.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author vregi, 12/13/2024
 */
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<Employee> createEmployeesByFaker(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public Employee updateEmployee(Integer id, Employee updatedEmployee) {
        Employee existingEmployee = getEmployeeById(id);
        existingEmployee.setFirstname(updatedEmployee.getFirstname());
        existingEmployee.setLastname(updatedEmployee.getLastname());
        existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setHireDate(updatedEmployee.getHireDate());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        existingEmployee.setPosition(updatedEmployee.getPosition());
        if (updatedEmployee.getDepartment() != null) {
            existingEmployee.setDepartment(updatedEmployee.getDepartment());
        }

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public List<EmployeeProjectDTO> getEmployeesWithProjectsAndDepartments() {
        return employeeRepository.findEmployeesWithProjectsAndDepartments();
    }

    @Transactional(readOnly = true)
    public List<EmployeeProjectDTO> getEmployeesByDepartmentWithHighHours(Integer departmentId) {
        return employeeRepository.findEmployeesByDepartmentWithHighHours(departmentId);
    }

    @Transactional(readOnly = true)
    public List<DepartmentStatsDTO> getDepartmentStatistics() {
        return employeeRepository.getDepartmentStatistics();
    }

}
