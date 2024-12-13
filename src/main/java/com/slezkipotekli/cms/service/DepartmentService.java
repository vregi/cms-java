package com.slezkipotekli.cms.service;

import com.slezkipotekli.cms.entity.Department;
import com.slezkipotekli.cms.entity.Employee;
import com.slezkipotekli.cms.repository.DepartmentRepository;
import com.slezkipotekli.cms.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author vregi, 12/13/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public List<Department> createDepartmentsByFaker(List<Department> departments) {
        for (Department department : departments) {
            List<Employee> employees = department.getEmployees();
            department.setEmployees(null);
            Department savedDepartment = departmentRepository.save(department);

            for (Employee employee : employees) {
                employee.setDepartment(savedDepartment);
                employeeRepository.save(employee);
            }

            department.setEmployees(employees);
        }
        return departments;
    }

    public Department updateDepartment(Integer id, Department updatedDepartment) {
        Department existingDepartment = getDepartmentById(id);
        existingDepartment.setName(updatedDepartment.getName());
        existingDepartment.setDescription(updatedDepartment.getDescription());
        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }
}
