package ru.gb.timesheet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.entity.Project;
import ru.gb.timesheet.entity.Timesheet;
import ru.gb.timesheet.service.ProjectService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> get(@PathVariable Long id) {
        Optional<Project> project = service.getById(id);

        if (project.isPresent()) return ResponseEntity.status(HttpStatus.OK).body(project.get());
        throw new NoSuchElementException();
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheets(@PathVariable Long id) {
        if (service.getTimesheets(id).isEmpty()) throw new NoSuchElementException();
        return ResponseEntity.status(HttpStatus.OK).body(service.getTimesheets(id));
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        project = service.create(project);

        return ResponseEntity.status(HttpStatus.OK).body(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        throw new NoSuchElementException();
    }
}
