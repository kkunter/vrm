package com.example.projectmanangment.controller;

import com.example.projectmanangment.domain.Employee;
import com.example.projectmanangment.domain.Project;
import com.example.projectmanangment.service.EmployeeService;
import com.example.projectmanangment.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = projectService.getAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }
    @GetMapping("/new")
    public String displayProjectForm(Model model) {

        Project aProject = new Project();
        Iterable<Employee> employees = employeeService.getAll();
        model.addAttribute("project", aProject);
        model.addAttribute("allEmployees", employees);

        return "projects/new-project";
    }
    @PostMapping("/save")
    public String createProject(@Valid Project project, @RequestParam List<Long> employees, Model model) {


        projectService.save(project);

        // use a redirect to prevent duplicate submissions
        return "redirect:/projects ";

    }
}
