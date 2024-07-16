package ru.gb.timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.timesheet.entity.Project;
import ru.gb.timesheet.entity.Timesheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ProjectRepository {

    private static Long quantity = 1L;
    private final List<Project> projects = new ArrayList<>();

    public Optional<Project> getById(Long id) {
        return projects.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();
    }

    public List<Project> getAll() {
        return List.copyOf(projects);
    }

    public Project create(Project project) {
        project.setId(quantity++);
        projects.add(project);
        return project;
    }

    public void delete(Long id) {
        projects.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(projects::remove);
    }

    public List<Timesheet> getTimesheets(Long id) {
        Optional<Project> project = getById(id);
        return project.map(Project::getTimesheets).orElse(null);
    }
}
