package util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import model.Framework;
import model.Link;
import model.Note;
import model.Problem;
import model.Project;
import model.Status;
import model.Task;

public class Validation extends Exception {

	private static final long serialVersionUID = 1L;

	public Validation() {

    }

    public Validation(String message) {
        super(message);
    }

    public Validation(String message, Throwable cause) {
        super(message, cause);
    }

    public void addMessage(String message, Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severity, message, " "));
    }

    public boolean isNULL(String field) {
        if (field.isEmpty()) {
            addMessage("O campo não pode ficar vazio", FacesMessage.SEVERITY_FATAL);
            return true;
        }
        return false;
    }

    public boolean isNULL(Status status) {
        if (status.getTitle().isEmpty() || status.getBackgroundColor().isEmpty()) {
            addMessage("Preencha todos os campos", FacesMessage.SEVERITY_FATAL);
            return true;
        }
        return false;
    }
    
    public boolean isNULL(Framework framework) {
        if (framework.getTitle().isEmpty() || framework.getType().isEmpty()) {
            addMessage("Preencha todos os campos", FacesMessage.SEVERITY_FATAL);
            return true;
        }
        return false;
    }
    
    public boolean isNULL(Project project) {
        if (project.getName().isEmpty() || project.getLanguage().getId().equals(0) || project.getIde().getId().equals(0) || project.getSystem().getId().equals(0) || project.getPattern().getId().equals(0) || project.getDescription().isEmpty()) {
            addMessage("Preencha todos os campos", FacesMessage.SEVERITY_FATAL);
            return true;
        }
        return false;
    }
    
    public boolean isNULL(Task task) {
    	if (task.getDescription().isEmpty()) {
			addMessage("A descrição deve ser inserida", FacesMessage.SEVERITY_FATAL);
			return true;
		}
    	return false;
    }
    
    public boolean isNULL(Problem problem) {
    	if (problem.getDescription().isEmpty()) {
			addMessage("O problema deve ser inserido", FacesMessage.SEVERITY_FATAL);
			return true;
		}
    	return false;
    }
    
    public boolean isNULL(Note note) {
    	if (note.getDescription().isEmpty()) {
			addMessage("A nota deve ser inserida", FacesMessage.SEVERITY_FATAL);
			return true;
		}
    	return false;
    }
    
    public boolean isNULL(Link link) {
    	if (link.getTitle().isEmpty() || link.getUrl().isEmpty() || link.getDescription().isEmpty()) {
			addMessage("Preencha Todos Os Campos", FacesMessage.SEVERITY_FATAL);
			return true;
		}
    	return false;
    }

    public String firstCharToUpperCase(String word) {
        String split[] = word.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < split.length; i++) {
            String w = split[i];
            w = w.substring(0, 1).toUpperCase() + w.substring(1);
            sb.append(" ").append(w);
        }
        return sb.toString().replaceFirst(" ", "");
    }

    public String formatColor(String color) {
        if (color.startsWith("#")) {
            return color;
        } else {
            String initial = "#";
            return initial + color;
        }
    }
    
    public String defineTextColor(String color) {
    	int r = Integer.parseInt(color.substring(1, 3), 16);
    	int g = Integer.parseInt(color.substring(3, 5), 16);
    	int b = Integer.parseInt(color.substring(5, 7), 16);
    	
    	float compare = (float) ((r * 299 + g * 587 + b * 114) / 1000);
    	if (compare > 128) {
			return "#ffffff";
		}else {
			return "#000000";
		}
    }
    
    public Date dateNow() {
    	Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.getTime();
	}
}
