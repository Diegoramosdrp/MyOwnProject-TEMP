package controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.ProjectsDAO;
import model.Project;
import model.Task;
import util.ControllerInterface;
import util.Validation;

@ManagedBean(name = "TaskController")
@SessionScoped
public class TaskController implements ControllerInterface<Task> {

	private Task task = new Task();
	private final ProjectsDAO<Project> projectsDAO = new ProjectsDAO<Project>();
	private final Validation validation = new Validation();
	private ArrayList<Task> tasks;
	private Project project = new Project();
	
	public TaskController() {
		
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public void save() {
		task.setDescription(validation.firstCharToUpperCase(task.getDescription()));
		if(task.getId() == null) {
			task.setFinished(false);
			projectsDAO.manipulationData(task, "saveTask");
		} else {
			projectsDAO.manipulationData(task, "update");
		}
		task = new Task();
	}
	
	public void save(Integer id) {
		if (!validation.isNULL(task)) {
			project = projectsDAO.findById(id);
			task.setProject(project);
			save();
		}
	}
	
	public void finishTask(Task task) {
		if (task.getId() != null) {
			task.setFinished(true);
			projectsDAO.manipulationData(task, "update");
		}
	}

	@Override
	public void remove(Task entity) {
		projectsDAO.manipulationData(entity, "remove");
        task = new Task();
	}

	@Override
	public void edit(Task entity) {
		this.task = entity;
	}

	@Override
	public void list() {
		// TODO Auto-generated method stub
	}

}
