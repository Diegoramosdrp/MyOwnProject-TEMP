package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import util.Details;

@Entity(name = "frameworks")
public class Framework extends Details{

    @Column
    private String Type;
    
    @ManyToMany(mappedBy = "frameworks")
    private List<Project> projects = new ArrayList<Project>();
    
    public Framework() {
        super();
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
}
