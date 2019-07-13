package controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import DAO.FilesDAO;

@ManagedBean(name = "DocumentController")
@SessionScoped
public class DocumentController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private UploadedFile file;
	private String projectName;
	private List<File> documents = new ArrayList<>();
	private static FilesDAO filesDAO = new FilesDAO();
	
	public DocumentController() {
		//documents = new ArrayList<>(filesDAO.listDocuments("new path", "files"));
	}
	
	public DocumentController(String projectName, String typePath) {
		documents = new ArrayList<>(filesDAO.listDocuments(projectName, typePath));
	}
	
	@PostConstruct
    public void postConstruct() {
        documents = new ArrayList<>(filesDAO.listDocuments("new path", "files"));
    }
	
	public List<File> getDocuments() {
		return documents;
	}

	public void setDocuments(List<File> documents) {
		this.documents = documents;
	}

	public UploadedFile getFile() {
		return file;
	}
	
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public void uploadDocument(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        
        try {
            @SuppressWarnings("static-access")
			File document = filesDAO.upload(getProjectName(), "files", uploadedFile.getFileName(), uploadedFile.getContents());
            
            documents.add(document);          
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public void uploadImage(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        
        try {
            @SuppressWarnings("static-access")
			File document = filesDAO.upload(getProjectName(), "images", uploadedFile.getFileName(), uploadedFile.getContents());
            
            documents.add(document);          
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	}
}