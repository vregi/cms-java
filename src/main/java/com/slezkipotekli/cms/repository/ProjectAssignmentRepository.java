package com.slezkipotekli.cms.repository;

import com.slezkipotekli.cms.entity.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vregi, 12/13/2024
 */
@Repository
public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Integer> {
}
