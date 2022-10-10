package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final UserService userService;
    private final ProjectService projectService;

    private final TaskService taskService;

    public TaskController(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/create")
    public String createTask(Model model){

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findAllEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "/task/create";
    }


    @PostMapping("/create")  // save function
    public String insertTask(TaskDTO task){

        taskService.save(task);

        return "redirect:/task/create";
    }

    @GetMapping("/delete/{id}")  // delete function
    public String deleteTask(@PathVariable("id") Long id){

        taskService.deleteById(id);

        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskId}") // update function - 1st step - retriving
    public String editTask(@PathVariable("taskId") Long taskId, Model model){

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findAllEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "/task/update";
    }

//    @PostMapping("/update/{taskId}")
//    public String updateTask(@PathVariable("taskId") Long taskId, TaskDTO task){
//
//        task.setId(taskId);
//
//        taskService.update(task);
//
//        return "redirect:/task/create";
//    }

    @PostMapping("/update/{id}") // update function - 2nd step - save/update this changes in DB(map)

    //{id} - we should put, because we don't have this id in the task form
    // whenever we click save after making changes, we need to catcher id of this taskDTO that we did change,
    // then we can set it to our new object that we are saving in DB

    // @PathVariable and .setId() - don't need to put in Spring-Boot, IF! : there is same filed name:
    // the field name in {} backers ({id}) exactly the same filed name in object TaskDto (id)
    // it is automatically parsing {id}-value to id field
    public String updateTask(TaskDTO task){

        taskService.update(task);

        return "redirect:/task/create";
    }


    @GetMapping("/employee/pending-tasks")
    public String employeePendingTasks(Model model){

        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETED));

        return "/task/pending-tasks";
    }

    @GetMapping("/employee/archive")
    public String employeeArchivedTasks(Model model){

        model.addAttribute("tasks", taskService.findAllTasksByStatusIs(Status.COMPLETED));

        return "/task/archive";
    }

    @GetMapping("/employee/edit/{id}")
    public String employeeEditTask(@PathVariable Long id, Model model){

        model.addAttribute("task", taskService.findById(id));
//        model.addAttribute("projects", projectService.findAll());
//        model.addAttribute("employees", userService.findAllEmployees());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETED));

       return "/task/status-update";

    }

    @PostMapping("/employee/update/{id}")
    public String employeeUpdateTask(TaskDTO task){

        taskService.updateStatus(task);

        return "redirect:/task/employee/pending-tasks";

    }



}
