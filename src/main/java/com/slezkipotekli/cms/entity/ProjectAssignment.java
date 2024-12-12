package com.slezkipotekli.cms.entity;

import jakarta.persistence.*;

/**
 * @author vregi, 12/13/2024
 */
@Entity
@Table(name = "project_assignments")
public class ProjectAssignment {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String role;

    @Column(name = "hours_allocated")
    private Integer hoursAllocated;
}
