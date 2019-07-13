package model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import util.Details;

@Entity(name = "systems")
public class System extends Details {

    @OneToMany(mappedBy = "system")
    private List<Project> projects;

    public System() {
        super();
    }
}
