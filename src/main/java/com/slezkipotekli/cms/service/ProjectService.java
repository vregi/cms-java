package com.slezkipotekli.cms.service;

import com.slezkipotekli.cms.entity.Project;
import com.slezkipotekli.cms.entity.ProjectAssignment;
import com.slezkipotekli.cms.repository.ProjectAssignmentRepository;
import com.slezkipotekli.cms.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vregi, 12/13/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectAssignmentRepository projectAssignmentRepository;
    private final EmployeeService employeeService;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Integer id, Project updatedProject) {
        Project existingProject = getProjectById(id);
        existingProject.setName(updatedProject.getName());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setStartDate(updatedProject.getStartDate());
        existingProject.setEndDate(updatedProject.getEndDate());
        existingProject.setClient(updatedProject.getClient());
        return projectRepository.save(existingProject);
    }

    @Transactional
    public List<Project> createProjectsByFaker(List<Project> projects) {
        List<Project> savedProjects = new ArrayList<>();

        for (Project project : projects) {
            List<ProjectAssignment> assignments = project.getProjectAssignments();
            project.setProjectAssignments(null);
            Project savedProject = projectRepository.save(project);

            if (assignments != null) {
                for (ProjectAssignment assignment : assignments) {
                    assignment.setProject(savedProject);
                    projectAssignmentRepository.save(assignment);
                }
            }

            savedProject.setProjectAssignments(assignments);
            savedProjects.add(savedProject);
        }

        return savedProjects;
    }

    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }

    public List<Project> createDepartmentsByFaker(List<Project> projects) {
        return projectRepository.saveAll(projects);
    }
}
