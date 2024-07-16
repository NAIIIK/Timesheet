package ru.gb.timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.timesheet.entity.Timesheet;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TimesheetRepository {
    private static Long sequence = 1L;
    private final List<Timesheet> timesheets = new ArrayList<>();

    public Optional<Timesheet> getById(Long id) {
        return timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();
    }

    public List<Timesheet> getAll() {
        return List.copyOf(timesheets);
    }

    public Timesheet create(Timesheet timesheet) {
        timesheet.setId(sequence++);
        timesheets.add(timesheet);
        return timesheet;
    }

    public void delete(Long id) {
        timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(timesheets::remove);
    }

    public List<Timesheet> getCreatedAfter(LocalDate date) {
        return timesheets.stream()
                .filter(timesheet -> timesheet.getCreatedAt().isAfter(date))
                .collect(Collectors.toList());
    }

    public List<Timesheet> getCreatedBefore(LocalDate date) {
        return timesheets.stream()
                .filter(timesheet -> timesheet.getCreatedAt().isBefore(date))
                .collect(Collectors.toList());
    }
}
