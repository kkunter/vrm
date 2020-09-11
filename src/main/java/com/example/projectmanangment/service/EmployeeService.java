package com.example.projectmanangment.service;

import com.example.projectmanangment.domain.Employee;
import com.example.projectmanangment.dto.EmployeeProject;
import com.example.projectmanangment.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Iterable<Employee> getAll() {
        return employeeRepository.findAll();
    }


    public List<EmployeeProject> employeeProjects() {
        return employeeRepository.employeeProjects();
    }


    public Employee findByEmployeeId(long theId) {

        return employeeRepository.findByEmployeeId(theId);
    }


    public void delete(Employee theEmp) {
        employeeRepository.delete(theEmp);

    }
}
