package com.cydeo.dto;

import com.cydeo.enums.Status;

import java.time.LocalDate;

public class TaskDTO {

    private Long id;

    private ProjectDTO project;
    private UserDTO assignedEmployee;
    private String taskSubject;
    private String taskDetail;
    private Status taskStatus;
    private LocalDate assignedDate;

}
