package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.RoleDTO;

public interface ProjectService extends CrudService<ProjectDTO,String> {

    void complete(ProjectDTO project);


}
