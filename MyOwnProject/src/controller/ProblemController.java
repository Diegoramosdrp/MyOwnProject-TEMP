package controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.ProjectsDAO;
import model.Project;
import model.Problem;
import util.ControllerInterface;
import util.Validation;

@ManagedBean(name = "ProblemController")
@SessionScoped
public class ProblemController implements ControllerInterface<Problem> {
	
	private Problem problem = new Problem();
	private final ProjectsDAO<Project> projectsDAO = new ProjectsDAO<Project>();
	private final Validation validation = new Validation();
	private ArrayList<Problem> problems;
	private Project project = new Project();
	
	public ProblemController() {
		
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public ArrayList<Problem> getProblems() {
		return problems;
	}

	public void setProblems(ArrayList<Problem> problems) {
		this.problems = problems;
	}

	@Override
	public void save() {
		problem.setDescription(validation.firstCharToUpperCase(problem.getDescription()));
		if(problem.getId() == null) {
			problem.setSolved(false);
			projectsDAO.manipulationData(problem, "saveProblem");
		} else {
			projectsDAO.manipulationData(problem, "update");
		}
		problem = new Problem();
	}
	
	public void save(Integer id) {
		if (!validation.isNULL(problem)) {
			project = projectsDAO.findById(id);
			problem.setProject(project);
			save();
		}
	}
	
	public void solveProblem(Problem problem) {
		if (problem.getId() != null) {
			problem.setSolved(true);
			projectsDAO.manipulationData(problem, "update");
		}
	}

	@Override
	public void remove(Problem entity) {
		projectsDAO.manipulationData(entity, "remove");
        problem = new Problem();
	}

	@Override
	public void edit(Problem entity) {
		this.problem = entity;
	}

	@Override
	public void list() {
		// TODO Auto-generated method stub
	}
}
