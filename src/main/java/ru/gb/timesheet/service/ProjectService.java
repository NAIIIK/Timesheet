package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.entity.Project;
import ru.gb.timesheet.entity.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository projectRepository) {
        this.repository = projectRepository;
    }

    public Optional<Project> getById(Long id) {
        return repository.getById(id);
    }

    public List<Project> getAll() {
        return repository.getAll();
    }

    public Project create(Project project) {
        return repository.create(project);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public List<Timesheet> getTimesheets(Long id) {
        return repository.getTimesheets(id);
    }

}
