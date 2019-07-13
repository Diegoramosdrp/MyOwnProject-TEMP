package managerFiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "FileController")
@SessionScoped
public class FileController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String path = "C:\\Users\\Usuario\\Desktop\\upload\\";
	private UploadedFile file;
	
	public FileController() {
		
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public void createPathProject(String nameProject) {
        try {
            File dir = new File("C:\\Users\\Usuario\\Desktop\\upload\\" + nameProject);;
            dir.mkdirs();
            
            File filesDir = new File(dir + "\\files\\");
            filesDir.mkdirs();
            
            File imagesDir = new File(dir + "\\images\\");
            imagesDir.mkdirs();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
	
	public void transferFile(String fileName, InputStream inputStream) {
		try {
			OutputStream outputStream =  new FileOutputStream(new File(path + "\\test\\" +fileName));
			int reader = 0;
			byte[] bytes = new byte[(int) getFile().getSize()];
			
			while((reader = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, reader);
			}
			
			inputStream.close();
			outputStream.flush();
			outputStream.close();
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void upload() {
		
		String extentionValidation;
		
		if(getFile() != null) {
			String extention = getFile().getFileName();
			
			if(extention != null) {
				extentionValidation = extention.substring(extention.indexOf(".") + 1);
			} else {
				extentionValidation=  "null";
			}
			
			if(extentionValidation.equals("pdf")) {
				try {
					transferFile(getFile().getFileName(), getFile().getInputstream());
				} catch (IOException ex) {
					Logger.getLogger(FileController.class.getName()).log(Level.ERROR, null, ex);
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("ERROR","Não foi possivel realizar o upload"));
				}
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Sucesso","Upload concluido"));
			}
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("ERROR","Escolha um arquivo"));
		}
	}
}
