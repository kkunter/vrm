package com.example.projectmanangment.controller;


import com.example.projectmanangment.domain.Project;
import com.example.projectmanangment.dto.ChartData;
import com.example.projectmanangment.dto.EmployeeProject;
import com.example.projectmanangment.service.EmployeeService;
import com.example.projectmanangment.service.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {


    private final ProjectService proService;
    private final EmployeeService employeeService;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {



        // we are querying the database for projects
        List<Project> projects = proService.getAll();
        model.addAttribute("projectsList", projects);

        List<ChartData> projectData = proService.getProjectStatus();

        // Lets convert projectData object into a json structure for use in javascript
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        // [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]

        model.addAttribute("projectStatusCnt", jsonString);

        // we are querying the database for employees
        List<EmployeeProject> employeesProjectCnt = employeeService.employeeProjects();
        model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);


        return "main/home";

    }
}
