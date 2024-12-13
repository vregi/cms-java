package com.slezkipotekli.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author vregi, 12/13/2024
 */
@Data
@AllArgsConstructor
public class EmployeeProjectDTO {
    private String firstname;
    private String lastname;
    private String departmentName;
    private String projectName;
    private String projectRole;
    private Integer hoursAllocated;

    public EmployeeProjectDTO(String firstname, String lastname,
                              String projectName, Integer hoursAllocated) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.projectName = projectName;
        this.hoursAllocated = hoursAllocated;
    }
}
