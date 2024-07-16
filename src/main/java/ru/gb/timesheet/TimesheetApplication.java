package ru.gb.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.timesheet.entity.Project;
import ru.gb.timesheet.entity.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TimesheetApplication.class, args);

		TimesheetRepository repository = context.getBean(TimesheetRepository.class);

		ProjectRepository projectRepository = context.getBean(ProjectRepository.class);

		for (int i = 1; i < 6 ; i++) {
			Project project = new Project();
			project.setId((long) i);
			project.setName("Project #" + i);
			projectRepository.create(project);
		}

		LocalDate createdAt = LocalDate.now();
		for (int i = 0; i < 10; i++) {
			createdAt = createdAt.plusDays(1);

			Timesheet timesheet = new Timesheet();
			timesheet.setId((long) i);
			timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

			repository.create(timesheet);
		}
	}

}
