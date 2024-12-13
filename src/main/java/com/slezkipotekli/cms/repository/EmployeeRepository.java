package com.slezkipotekli.cms.repository;

import com.slezkipotekli.cms.dto.DepartmentStatsDTO;
import com.slezkipotekli.cms.dto.EmployeeProjectDTO;
import com.slezkipotekli.cms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vregi, 12/13/2024
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("""
        SELECT NEW com.slezkipotekli.cms.dto.EmployeeProjectDTO(
            e.firstname, e.lastname, d.name, p.name, pa.role, pa.hoursAllocated
        )
        FROM Employee e
        JOIN e.department d
        LEFT JOIN e.projectAssignments pa
        LEFT JOIN pa.project p
        ORDER BY e.lastname, e.firstname
    """)
    List<EmployeeProjectDTO> findEmployeesWithProjectsAndDepartments();

    @Query("""
        SELECT NEW com.slezkipotekli.cms.dto.EmployeeProjectDTO(
            e.firstname, e.lastname, p.name, pa.hoursAllocated
        )
        FROM Employee e
        JOIN e.projectAssignments pa
        JOIN pa.project p
        WHERE e.department.id = :departmentId
        AND pa.hoursAllocated > 20
        ORDER BY pa.hoursAllocated DESC
    """)
    List<EmployeeProjectDTO> findEmployeesByDepartmentWithHighHours(
            @Param("departmentId") Integer departmentId
    );

    @Query("""
        SELECT NEW com.slezkipotekli.cms.dto.DepartmentStatsDTO(
            d.name,
            COUNT(e.id),
            AVG(e.salary),
            MAX(e.salary),
            MIN(e.salary)
        )
        FROM Department d
        LEFT JOIN d.employees e
        GROUP BY d.id, d.name
        HAVING COUNT(e.id) > 0
        ORDER BY AVG(e.salary) DESC
    """)
    List<DepartmentStatsDTO> getDepartmentStatistics();
}
