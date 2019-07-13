package model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import util.Details;

@Entity(name = "ides")
public class IDE extends Details {

    @OneToMany(mappedBy = "ide")
    private List<Project> projects;

    public IDE() {
        super();
    }
}
