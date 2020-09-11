package com.example.projectmanangment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private long employeeId;

    @NotBlank(message="Must give a first name")
    @Size(min=2, max=50)
    private String firstName;

    @NotBlank(message="Must give a last name")
    @Size(min=1, max=50)
    private String lastName;

    @NotBlank
    @Email(message="Must be a valid email address")
    private String email;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name="project_employee",
            joinColumns=@JoinColumn(name="employee_id"),
            inverseJoinColumns= @JoinColumn(name="project_id")
    )
    @JsonIgnore
    private List<Project> projects;
}
