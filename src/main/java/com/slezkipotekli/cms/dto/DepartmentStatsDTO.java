package com.slezkipotekli.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author vregi, 12/13/2024
 */
@Data
@AllArgsConstructor
public class DepartmentStatsDTO {
    private String departmentName;
    private Long employeeCount;
    private Double averageSalary;
    private BigDecimal maxSalary;
    private BigDecimal minSalary;
}
