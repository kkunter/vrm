package com.example.projectmanangment.service;

import com.example.projectmanangment.domain.Project;
import com.example.projectmanangment.dto.ChartData;
import com.example.projectmanangment.dto.TimeChartData;
import com.example.projectmanangment.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project save(Project project) {
        return projectRepository.save(project);
    }


    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public List<ChartData> getProjectStatus(){
        return projectRepository.getProjectStatus();
    }


    public List<TimeChartData> getTimeData(){
        return projectRepository.getTimeData();
    }
}
