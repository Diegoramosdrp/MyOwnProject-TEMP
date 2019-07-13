package controller;

import DAO.DetailsDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Framework;
import util.ControllerInterface;
import util.Validation;

@ManagedBean(name = "FrameworkController")
@SessionScoped
public class FrameworkController implements ControllerInterface<Framework> {

    private Framework framework = new Framework();
    private final DetailsDAO<Framework> detailsDAO = new DetailsDAO<>();
    private ArrayList<Framework> frameworkList;
    public final Validation validation = new Validation();
    private ArrayList<String> type = new ArrayList<>();
    private String title;

    public FrameworkController() {
        this.frameworkList = detailsDAO.listAll(Framework.class);
        this.type.add("Front-end");
        this.type.add("Back-end");
    }

    public Framework getFramework() {
        return framework;
    }

    public void setFramework(Framework framework) {
        this.framework = framework;
    }

    public ArrayList<Framework> getFrameworkList() {
        return frameworkList;
    }

    public void setFrameworkList(ArrayList<Framework> frameworkList) {
        this.frameworkList = frameworkList;
    }

    public ArrayList<String> getType() {
        return type;
    }

    @Override
    public void save() {
        if (!validation.isNULL(framework)) {
            framework.setTitle(validation.firstCharToUpperCase(framework.getTitle()));
            if (framework.getId() == null) {
                if (!detailsDAO.findByName(framework.getTitle(), Framework.class)) {
                    detailsDAO.manipulationData(framework, "save");
                }
            } else {
                if (!framework.getTitle().equals(title)) {
                    if (!detailsDAO.findByName(framework.getTitle(), Framework.class)) {
                        detailsDAO.manipulationData(framework, "update");
                    }
                } else {
                    detailsDAO.manipulationData(framework, "update");
                }
            }
        }
        framework = new Framework();
        list();
    }

    @Override
    public void remove(Framework entity) {
        detailsDAO.manipulationData(entity, "remove");
        framework = new Framework();
        list();
    }

    @Override
    public void edit(Framework entity) {
        this.framework = entity;
        this.title = entity.getTitle();
    }

    @Override
    public void list() {
        frameworkList = detailsDAO.listAll(Framework.class);
    }
}
