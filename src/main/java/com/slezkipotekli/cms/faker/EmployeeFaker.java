package com.slezkipotekli.cms.faker;

import com.github.javafaker.Faker;
import com.slezkipotekli.cms.entity.Employee;

import java.math.BigDecimal;

/**
 * @author vregi, 12/13/2024
 */
public class EmployeeFaker {
    private static final Faker faker = new Faker();

    public static Employee createFakeEmployee() {
        Employee employee = new Employee();
        employee.setFirstname(faker.name().firstName());
        employee.setLastname(faker.name().lastName());
        employee.setPhoneNumber(faker.phoneNumber().phoneNumber());
        employee.setEmail(faker.internet().emailAddress());
        employee.setHireDate(new java.sql.Date(faker.date().past(365, java.util.concurrent.TimeUnit.DAYS).getTime()));
        employee.setSalary(BigDecimal.valueOf(faker.number().randomDouble(2, 3000, 10000)));
        employee.setPosition(faker.job().title());

        return employee;
    }
}
