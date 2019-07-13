package model;

import java.util.List;
import util.Details;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "dbs")
public class Database extends Details{

    @OneToMany(mappedBy = "database")
    private List<Project> projects;
    
    public Database() {
        super();
    }
}