package com.example.projectmanangment.controller;

import com.example.projectmanangment.domain.Employee;
import com.example.projectmanangment.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public String displayEmployees(Model model) {
        Iterable<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {

        Employee anEmployee = new Employee();

        model.addAttribute("employee", anEmployee);

        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(Model model, @Valid Employee employee, Errors errors) {

        if(errors.hasErrors())
            return "employees/new-employee";

        // save to the database using an employee crud repository
        employeeService.save(employee);

        return "redirect:/employees";
    }

    @GetMapping("/update")
    public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {

        Employee theEmp = employeeService.findByEmployeeId(theId);

        model.addAttribute("employee", theEmp);


        return "employees/new-employee";
    }

    @GetMapping("delete")
    public String deleteEmployee(@RequestParam("id") long theId, Model model) {
        Employee theEmp = employeeService.findByEmployeeId(theId);
        employeeService.delete(theEmp);
        return "redirect:/employees";
    }

}
