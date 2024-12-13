package com.slezkipotekli.cms.faker;

import com.github.javafaker.Faker;
import com.slezkipotekli.cms.entity.Department;
import com.slezkipotekli.cms.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author vregi, 12/13/2024
 */
@Component
@RequiredArgsConstructor
public class DepartmentFaker {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    private static final String[] DEPARTMENT_TYPES = {
            "Engineering", "Marketing", "Sales", "Finance", "HR",
            "Research", "Development", "Support", "Operations", "Legal",
            "Product", "Design", "Quality Assurance", "Analytics", "Security"
    };

    public static Department createFakeDepartment(List<Employee> availableEmployees) {
        Department department = new Department();

        String deptType = DEPARTMENT_TYPES[random.nextInt(DEPARTMENT_TYPES.length)];
        String deptName = deptType + " " + faker.company().buzzword();

        department.setName(deptName);
        department.setDescription(faker.company().catchPhrase());

        int employeeCount = random.nextInt(6) + 3;
        List<Employee> departmentEmployees = new ArrayList<>();

        for (int i = 0; i < employeeCount && !availableEmployees.isEmpty(); i++) {
            int randomIndex = random.nextInt(availableEmployees.size());
            Employee employee = availableEmployees.remove(randomIndex);
            departmentEmployees.add(employee);
        }

        department.setEmployees(departmentEmployees);

        return department;
    }

}