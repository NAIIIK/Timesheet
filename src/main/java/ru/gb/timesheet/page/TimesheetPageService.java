package ru.gb.timesheet.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.entity.Project;
import ru.gb.timesheet.entity.Timesheet;
import ru.gb.timesheet.service.ProjectService;
import ru.gb.timesheet.service.TimesheetService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {

    private final TimesheetService timesheetService;
    private final ProjectService projectService;

    public Optional<TimesheetPageDto> findById(Long id) {
        return timesheetService.getById(id)
                .map(this::convert);
    }

    public List<TimesheetPageDto> findAll() {
        return timesheetService.getAll().stream()
                .map(this::convert)
                .toList();
    }

    private TimesheetPageDto convert(Timesheet timesheet) {
        Project project = projectService.getById(timesheet.getProjectId()).orElseThrow();

        TimesheetPageDto timesheetPageDto = new TimesheetPageDto();

        timesheetPageDto.setId(String.valueOf(timesheet.getId()));
        timesheetPageDto.setProjectName(project.getName());
        timesheetPageDto.setProjectId(String.valueOf(project.getId()));
        timesheetPageDto.setMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageDto.setCreatedAt(timesheet.getCreatedAt().toString());

        return timesheetPageDto;
    }

}
