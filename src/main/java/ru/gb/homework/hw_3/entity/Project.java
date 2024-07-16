package ru.gb.homework.hw_3.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Project {

    private Long id;
    private String name;
    private List<Timesheet> timesheets = new ArrayList<>();
}
