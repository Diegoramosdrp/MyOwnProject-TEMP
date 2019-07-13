package controller;

import DAO.DetailsDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.IDE;
import util.ControllerInterface;
import util.Validation;

@ManagedBean(name = "IDEController")
@SessionScoped
public class IDEController implements ControllerInterface<IDE>{
    
    private IDE ide = new IDE();
    private final DetailsDAO<IDE> detailsDAO = new DetailsDAO<>();
    private ArrayList<IDE> ideList;
    public final Validation validation = new Validation();

    public IDEController() {
        this.ideList = detailsDAO.listAll(IDE.class);
    }

    public IDE getIde() {
        return ide;
    }

    public void setIde(IDE ide) {
        this.ide = ide;
    }

    public ArrayList<IDE> getIdeList() {
        return ideList;
    }

    public void setIdeList(ArrayList<IDE> ideList) {
        this.ideList = ideList;
    }

    @Override
    public void save() {
        if (!validation.isNULL(ide.getTitle())) {
            if (!detailsDAO.findByName(ide.getTitle(), IDE.class)) {
                ide.setTitle(validation.firstCharToUpperCase(ide.getTitle()));
                if (ide.getId() == null) {
                    detailsDAO.manipulationData(ide, "save");
                } else {
                    detailsDAO.manipulationData(ide, "update");
                }
            }
        }
        ide = new IDE();
        list();
    }

    @Override
    public void remove(IDE entity) {
        detailsDAO.manipulationData(entity, "remove");
        ide = new IDE();
        list();
    }

    @Override
    public void edit(IDE entity) {
        this.ide = entity;
    }

    @Override
    public void list() {
        ideList = detailsDAO.listAll(IDE.class);
    }
}
