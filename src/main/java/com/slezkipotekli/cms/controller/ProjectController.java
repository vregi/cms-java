package com.slezkipotekli.cms.controller;

import com.slezkipotekli.cms.entity.Department;
import com.slezkipotekli.cms.entity.Employee;
import com.slezkipotekli.cms.entity.Project;
import com.slezkipotekli.cms.faker.DepartmentFaker;
import com.slezkipotekli.cms.faker.ProjectFaker;
import com.slezkipotekli.cms.service.EmployeeService;
import com.slezkipotekli.cms.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author vregi, 12/13/2024
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
@Slf4j
public class ProjectController {
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Integer id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @PostMapping("/faker")
    public List<Project> createProjectsByFaker() {
        // Отримуємо всіх доступних співробітників
        List<Employee> availableEmployees = new ArrayList<>(employeeService.getAllEmployees());

        List<Project> projects = Stream
                .generate(() -> ProjectFaker.createFakeProject(availableEmployees))
                .limit(5)
                .toList();

        return projectService.createProjectsByFaker(projects);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Integer id, @RequestBody Project project) {
        return projectService.updateProject(id, project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
    }
}
