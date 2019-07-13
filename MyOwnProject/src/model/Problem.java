package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "problems")
public class Problem {
	
	public Problem() {
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer Id;

    @Column
    private String Description;
    
    @Column
    private boolean Solved;
    
    @ManyToOne
    @JoinColumn(name = "ProjectId")
    private Project project = new Project();

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public boolean isSolved() {
		return Solved;
	}

	public void setSolved(boolean solved) {
		Solved = solved;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
