package DAO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;

import util.Validation;

public class FilesDAO {
	
	private static Validation validation = new Validation();
	private static String path = "C:\\Users\\Usuario\\Desktop\\upload\\";
	
	public void createPathProject(String nameProject) {
        try {
            File dir = new File(path + nameProject);
            dir.mkdirs();
            
            File filesDir = new File(dir + "\\files\\");
            filesDir.mkdirs();
            
            File imagesDir = new File(dir + "\\images\\");
            imagesDir.mkdirs();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
	
	public static File upload(String projectName, String typePath, String fileName, byte[] contents) throws IOException {
        File dir = new File(path + projectName + "\\" + typePath, fileName);
        
        OutputStream out = new FileOutputStream(dir);
        out.write(contents);
        out.close();
        
        validation.addMessage("Arquivo(s) adicionado com sucesso", FacesMessage.SEVERITY_INFO);

        return dir;
	}
	
	public List<File> listDocuments(String projectName, String typePath) {
        File dir = new File(path + projectName + "\\" + typePath);

        return Arrays.asList(dir.listFiles());
	}
}