package ru.gb.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.timesheet.service.TimesheetPageService;

import java.util.NoSuchElementException;
import java.util.Optional;


@Controller
@RequestMapping("/home/timesheets")
@RequiredArgsConstructor
public class TimesheetPageController {

    private final TimesheetPageService service;

    @GetMapping("/{id}")
    public String getTimesheetPage(@PathVariable Long id, Model model) {
        Optional<TimesheetPageDto> timesheetPageDto = service.findById(id);

        if (timesheetPageDto.isEmpty()) {
            //return not-found.html
            throw new NoSuchElementException();
        }

        model.addAttribute("timesheet", timesheetPageDto.get());

        return "timesheet-page.html";
    }
}
