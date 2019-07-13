package controller;

import DAO.FilesDAO;
import DAO.ProjectsDAO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Framework;
import model.Project;
import model.Status;
import util.ControllerInterface;
import util.Validation;

@ManagedBean(name = "ProjectController")
@SessionScoped
public class ProjectController implements ControllerInterface<Project> {

    private Project project = new Project();
    private final ProjectsDAO<Project> projectsDAO = new ProjectsDAO<Project>();
    private final FilesDAO filesDAO = new FilesDAO();
    private ArrayList<Project> projectList;
    private ArrayList<Framework> frameworkList;
    public final Validation validation = new Validation();
    private Status status = new Status();
    private List<File> documentsList = new ArrayList<>();
    private List<File> imagesList = new ArrayList<>();

	public ProjectController() {
		project = new Project();
    	this.projectList = projectsDAO.currentsProjects(Project.class, isFinished());
    }

    public Project getProject() {
        return project;
    }

	public void setProject(Project project) {
        this.project = project;
    }

    public ArrayList<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(ArrayList<Project> projectList) {
		this.projectList = projectList;
	}

	public ArrayList<Framework> getFrameworkList() {
		return frameworkList;
	}

	public void setFrameworkList(ArrayList<Framework> frameworkList) {
		this.frameworkList = frameworkList;
	}

	public List<File> getDocumentsList() {
		return documentsList;
	}

	public void setDocumentsList(List<File> documentsList) {
		this.documentsList = documentsList;
	}

	public List<File> getImagesList() {
		return imagesList;
	}

	public void setImagesList(List<File> imagesList) {
		this.imagesList = imagesList;
	}

	@Override
    public void save() {
    	if (!validation.isNULL(project)) {
        	status = projectsDAO.getStatusDefault("Aguardando Inicio");
        	project.setStatus(status);
        	project.setFrameworks(frameworkList);
        	if (project.getDatabase().getId() == null) {
        		project.getDatabase().setId(null);
        	}
            
        	project.setDateTimeCreate(validation.dateNow());        	
        	projectsDAO.manipulationData(project, "saveProject");
        	filesDAO.createPathProject(project.getName());
        }
    	project = new Project();
    	list();
    }

    @Override
    public void remove(Project entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Project entity) {
        this.project = entity;
    }

    @Override
    public void list() {
    	this.projectList = projectsDAO.currentsProjects(Project.class, isFinished());
    }
    
    public void projectDetail(Integer id) {
    	project = projectsDAO.findById(id);
    	documentsList = filesDAO.listDocuments(project.getName(), "files");
    	imagesList =  filesDAO.listDocuments(project.getName(), "images");
    	
    	/* remove this block after finish the project*/
    	if (documentsList.isEmpty()) {
    		documentsList = filesDAO.listDocuments("new path", "files");
		}
    	
    	if (imagesList.isEmpty()) {
    		imagesList = filesDAO.listDocuments("new path", "images");
		}
    }
    
    
    public Integer isFinished() {
    	status = projectsDAO.getStatusDefault("Finalizado");    
    	return status.getId();
    }
}
