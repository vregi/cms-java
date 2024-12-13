package com.slezkipotekli.cms.repository;

import com.slezkipotekli.cms.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vregi, 12/13/2024
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
