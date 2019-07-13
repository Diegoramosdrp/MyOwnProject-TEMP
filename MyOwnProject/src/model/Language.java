package model;

import java.util.List;
import util.Details;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "languages")
public class Language extends Details{

    @OneToMany(mappedBy = "language")
    private List<Project> projects;

    public Language() {
        super();
    }
}