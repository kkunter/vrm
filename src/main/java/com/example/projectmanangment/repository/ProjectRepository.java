package com.example.projectmanangment.repository;

import com.example.projectmanangment.domain.Project;
import com.example.projectmanangment.dto.ChartData;
import com.example.projectmanangment.dto.TimeChartData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    public List<Project> findAll();

    @Query(nativeQuery=true, value="SELECT stage as label, COUNT(*) as value " +
            "FROM projects " +
            "GROUP BY stage")
    public List<ChartData> getProjectStatus();

    @Query(nativeQuery=true, value="SELECT name as projectName, start_date as startDate, end_date as endDate"
            + " FROM projects WHERE start_date is not null")
    public List<TimeChartData> getTimeData();
}
