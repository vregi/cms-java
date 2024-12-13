package com.slezkipotekli.cms.faker;

import com.github.javafaker.Faker;
import com.slezkipotekli.cms.entity.Client;
import com.slezkipotekli.cms.entity.Employee;
import com.slezkipotekli.cms.entity.Project;
import com.slezkipotekli.cms.entity.ProjectAssignment;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author vregi, 12/13/2024
 */
public class ProjectFaker {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    private static final String[] PROJECT_ROLES = {
            "Developer", "Tech Lead", "QA Engineer", "Business Analyst",
            "Project Manager", "Designer", "DevOps Engineer"
    };

    public static Project createFakeProject(List<Employee> availableEmployees) {
        Project project = new Project();

        project.setId(null);
        project.setName(faker.commerce().productName() + " " + faker.company().buzzword());
        project.setDescription(faker.lorem().paragraph());

        Date startDate = randomDateBetween(2020, 2023);
        Date endDate = randomDateAfter(startDate, 2025);
        project.setStartDate(startDate);
        project.setEndDate(endDate);

        Client client = new Client();
        client.setId(ThreadLocalRandom.current().nextInt(1, 30));
        project.setClient(client);

        int numberOfAssignments = random.nextInt(5) + 3;
        List<ProjectAssignment> assignments = new ArrayList<>();

        for (int i = 0; i < numberOfAssignments && !availableEmployees.isEmpty(); i++) {
            ProjectAssignment assignment = new ProjectAssignment();

            int employeeIndex = random.nextInt(availableEmployees.size());
            Employee employee = availableEmployees.get(employeeIndex);

            assignment.setEmployee(employee);
            assignment.setProject(project);
            assignment.setRole(PROJECT_ROLES[random.nextInt(PROJECT_ROLES.length)]);
            assignment.setHoursAllocated(random.nextInt(81) + 20); // 20-100 годин

            assignments.add(assignment);
        }

        project.setProjectAssignments(assignments);

        return project;
    }

    private static Date randomDateBetween(int startYear, int endYear) {
        LocalDate start = LocalDate.of(startYear, 1, 1);
        LocalDate end = LocalDate.of(endYear, 12, 31);

        long startEpochDay = start.toEpochDay();
        long endEpochDay = end.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);

        return Date.valueOf(LocalDate.ofEpochDay(randomDay));
    }

    private static Date randomDateAfter(Date startDate, int maxEndYear) {
        LocalDate start = startDate.toLocalDate().plusDays(1);
        LocalDate end = LocalDate.of(maxEndYear, 12, 31);

        long startEpochDay = start.toEpochDay();
        long endEpochDay = end.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);

        return Date.valueOf(LocalDate.ofEpochDay(randomDay));
    }
}
