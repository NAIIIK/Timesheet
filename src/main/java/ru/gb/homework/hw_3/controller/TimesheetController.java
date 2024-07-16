package ru.gb.homework.hw_3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.homework.hw_3.service.ProjectService;
import ru.gb.homework.hw_3.service.TimesheetService;
import ru.gb.homework.hw_3.entity.Timesheet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {
/*
    GET - получить - не содержит тела
    POST - создать
    PUT - изменить целиком
    PATCH - изменить выборочно
    DELETE - удалить

    @GetMapping("/timesheets/{id}") // получить конкретную запись по идентификатору
    @DeleteMapping("/timesheets/{id}") // удалить конкретную запись по идентификатору
    @PutMapping("/timesheets/{id}") // обновить конкретную запись по идентификатору

 */

    private final TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService, ProjectService projectService) {
        this.timesheetService = timesheetService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> get(@PathVariable Long id) {
        Optional<Timesheet> ts = timesheetService.getById(id);

        if (ts.isPresent()) {
//            return ResponseEntity.ok().body(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping // получить все
    public ResponseEntity<List<Timesheet>> getAll(@RequestParam(value = "createdAfter", required = false) String dateAfter,
                                                  @RequestParam(value = "createdBefore", required = false) String dateBefore) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        if (dateAfter != null) {
            LocalDate createdAfter = LocalDate.parse(dateAfter, formatter);

            List<Timesheet> timesheetsAfter = timesheetService.getCreatedAfter(createdAfter);

            if (timesheetsAfter.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.status(HttpStatus.OK).body(timesheetsAfter);
        }

        if (dateBefore != null) {
            LocalDate createdBefore = LocalDate.parse(dateBefore, formatter);

            List<Timesheet> timesheetsBefore = timesheetService.getCreatedBefore(createdBefore);

            if (timesheetsBefore.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.status(HttpStatus.OK).body(timesheetsBefore);
        }

        return ResponseEntity.status(HttpStatus.OK).body(timesheetService.getAll());
    }

    @PostMapping
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        timesheet = timesheetService.create(timesheet);

        if (timesheet == null) return ResponseEntity.notFound().build();

        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        timesheetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
