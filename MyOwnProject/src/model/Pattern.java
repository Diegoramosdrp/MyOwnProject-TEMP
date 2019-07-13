package model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import util.Details;

@Entity(name = "patterns")
public class Pattern extends Details {

    @OneToMany(mappedBy = "pattern")
    private List<Project> projects;

    public Pattern() {
        super();
    }
}
