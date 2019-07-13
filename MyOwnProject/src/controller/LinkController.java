package controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.ProjectsDAO;
import model.Link;
import model.Project;
import util.ControllerInterface;
import util.Validation;

@ManagedBean(name = "LinkController")
@SessionScoped
public class LinkController implements ControllerInterface<Link>{
	
	private Link link = new Link();
	private final ProjectsDAO<Project> projectsDAO = new ProjectsDAO<Project>();
	private final Validation validation = new Validation();
	private ArrayList<Link> links;
	private Project project = new Project();
	
	public LinkController() {
		
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public ArrayList<Link> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}

	@Override
	public void save() {
		link.setDescription(validation.firstCharToUpperCase(link.getDescription()));
		if(link.getId() == null) {
			projectsDAO.manipulationData(link, "saveLink");
		} else {
			projectsDAO.manipulationData(link, "update");
		}
		link = new Link();
	}
	
	public void save(Integer id) {
		if (!validation.isNULL(link)) {
			project = projectsDAO.findById(id);
			link.setProject(project);
			save();
		}
	}

	@Override
	public void remove(Link entity) {
		projectsDAO.manipulationData(entity, "remove");
        link = new Link();
	}

	@Override
	public void edit(Link entity) {
		this.link = entity;
		
	}

	@Override
	public void list() {
		// TODO Auto-generated method stub
	}
}
