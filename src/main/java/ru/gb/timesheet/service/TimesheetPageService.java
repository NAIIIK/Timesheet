package ru.gb.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.controller.TimesheetPageDto;
import ru.gb.timesheet.entity.Project;
import ru.gb.timesheet.entity.Timesheet;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {

    private final TimesheetService timesheetService;
    private final ProjectService projectService;

    public Optional<TimesheetPageDto> findById(Long id) {
        Optional<Timesheet> timesheetOpt = timesheetService.getById(id);

        if (timesheetOpt.isPresent()) {
            Timesheet timesheet = timesheetOpt.get();

            Project project = projectService.getById(timesheet.getProjectId()).orElseThrow();

            TimesheetPageDto timesheetPageDto = new TimesheetPageDto();

            timesheetPageDto.setProjectName(project.getName());
            timesheetPageDto.setId(String.valueOf(timesheet.getId()));
            timesheetPageDto.setMinutes(String.valueOf(timesheet.getMinutes()));
            timesheetPageDto.setCreatedAt(timesheet.getCreatedAt().toString());

            return Optional.of(timesheetPageDto);
        }
        return Optional.empty();
    }

}
