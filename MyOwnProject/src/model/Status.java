package model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import util.Details;

@Entity(name = "statuses")
public class Status extends Details{

    @Column
    private String BackgroundColor;
    
    @Column
    private String FontColor;
    
    @Column
    private int AllowDelete = 1;
    
    @OneToMany(mappedBy = "status")
    private List<Project> projects;
    
    public Status() {
        super();
    }

    public String getBackgroundColor() {
        return BackgroundColor;
    }

    public void setBackgroundColor(String BackgroundColor) {
        this.BackgroundColor = BackgroundColor;
    }

    public String getFontColor() {
		return FontColor;
	}

	public void setFontColor(String fontColor) {
		FontColor = fontColor;
	}

	public int getAllowDelete() {
        return AllowDelete;
    }

    public void setAllowDelete(int AllowDelete) {
        this.AllowDelete = AllowDelete;
    }
}