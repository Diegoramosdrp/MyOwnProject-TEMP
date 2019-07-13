package controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.ProjectsDAO;
import model.Note;
import model.Project;
import util.ControllerInterface;
import util.Validation;

@ManagedBean(name = "NoteController")
@SessionScoped
public class NoteController implements ControllerInterface<Note>{
	
	private Note note = new Note();
	private final ProjectsDAO<Project> projectsDAO = new ProjectsDAO<Project>();
	private final Validation validation = new Validation();
	private ArrayList<Note> notes;
	private Project project = new Project();
	
	public NoteController() {
		
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}

	@Override
	public void save() {
		note.setDescription(validation.firstCharToUpperCase(note.getDescription()));
		if(note.getId() == null) {
			projectsDAO.manipulationData(note, "saveNote");
		} else {
			projectsDAO.manipulationData(note, "update");
		}
		note = new Note();
	}
	
	public void save(Integer id) {
		if (!validation.isNULL(note)) {
			project = projectsDAO.findById(id);
			note.setProject(project);
			save();
		}
	}

	@Override
	public void remove(Note entity) {
		projectsDAO.manipulationData(entity, "remove");
        note = new Note();
		
	}

	@Override
	public void edit(Note entity) {
		this.note = entity;
	}

	@Override
	public void list() {
		// TODO Auto-generated method stub
	}
}
