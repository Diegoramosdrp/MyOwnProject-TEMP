package model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "projects")
public class Project implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer Id;
    
    @Column
    private String Name;
    
    @Column
    private String GitHub;
    
    @Column
    private String Description;
    
    @ManyToOne
    @JoinColumn(name = "Status_Id")
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "Language_Id")
    private Language language = new Language();
    
    @ManyToOne
    @JoinColumn(name = "Pattern_Id")
    private Pattern pattern = new Pattern();
    
    @ManyToOne
    @JoinColumn(name = "System_Id")
    private System system = new System(); 
    
    @ManyToOne
    @JoinColumn(name = "IDE_Id")
    private IDE ide = new IDE();
    
    @ManyToOne
    @JoinColumn(name = "Dbs_Id")
    private Database database = new Database();
    
    @Column
    private Date DateTimeCreate;
    
    @Column
    private Date DateTimeStart;
    
    @Column
    private Date DateTimeEnd;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "projects_frameworks", 
    		joinColumns = { @JoinColumn(name = "ProjectId", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "FrameworkId", nullable = false, updatable = false) })
    private List<Framework> frameworks;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<Task> taskList = new ArrayList<Task>();
    
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Problem> problemList = new ArrayList<Problem>();
    
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Note> noteList = new ArrayList<Note>();
    
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Link> linkList = new ArrayList<Link>();

    public Project() {
    }

    public Project(Project project, Framework framework) {
		
	}

	public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getGitHub() {
        return GitHub;
    }

    public void setGitHub(String GitHub) {
        this.GitHub = GitHub;
    }
    
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public IDE getIde() {
        return ide;
    }

    public void setIde(IDE ide) {
        this.ide = ide;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public Date getDateTimeCreate() {
        return DateTimeCreate;
    }

    public void setDateTimeCreate(Date DateTimeCreate) {
        this.DateTimeCreate = DateTimeCreate;
    }

    public Date getDateTimeStart() {
        return DateTimeStart;
    }

    public void setDateTimeStart(Date DateTimeStart) {
        this.DateTimeStart = DateTimeStart;
    }

    public Date getDateTimeEnd() {
        return DateTimeEnd;
    }

    public void setDateTimeEnd(Date DateTimeEnd) {
        this.DateTimeEnd = DateTimeEnd;
    }

	public List<Framework> getFrameworks() {
		return frameworks;
	}

	public void setFrameworks(List<Framework> frameworks) {
		this.frameworks = frameworks;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public List<Problem> getProblemList() {
		return problemList;
	}

	public void setProblemList(List<Problem> problemList) {
		this.problemList = problemList;
	}

	public List<Note> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<Note> noteList) {
		this.noteList = noteList;
	}

	public List<Link> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<Link> linkList) {
		this.linkList = linkList;
	}
}
