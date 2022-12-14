package com.sda.j113.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Paweł Recław, AmeN
 * @project j113_spring
 * @created 10.09.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserDTO {
    private Long id;
    private String login;
    private String name;
    private String surname;
    private List<String> roles;
}
