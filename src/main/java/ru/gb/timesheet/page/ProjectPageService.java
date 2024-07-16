package ru.gb.timesheet.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.entity.Project;
import ru.gb.timesheet.service.ProjectService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectPageService {

    private final ProjectService projectService;

    public Optional<ProjectPageDto> findById(Long id) {
        return projectService.getById(id)
                .map(this::convert);
    }

    public List<ProjectPageDto> findAll() {
        return projectService.getAll().stream()
                .map(this::convert)
                .toList();
    }

    private ProjectPageDto convert(Project project) {
        ProjectPageDto projectDto = new ProjectPageDto();

        projectDto.setId(String.valueOf(project.getId()));
        projectDto.setName(project.getName());
        projectDto.setTimesheets(project.getTimesheets().toString());

        return projectDto;
    }
}
