package controller;

import DAO.DetailsDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.ControllerInterface;
import util.Validation;
import model.System;

@ManagedBean(name = "SystemController")
@SessionScoped
public class SystemController implements ControllerInterface<System> {

    private System system = new System();
    private final DetailsDAO<System> detailsDAO = new DetailsDAO<>();
    private ArrayList<System> systemList;
    public final Validation validation = new Validation();

    public SystemController() {
        this.systemList = detailsDAO.listAll(System.class);
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public ArrayList<System> getSystemList() {
        return systemList;
    }

    public void setSystemList(ArrayList<System> systemList) {
        this.systemList = systemList;
    }

    @Override
    public void save() {
        if (!validation.isNULL(system.getTitle())) {
            if (!detailsDAO.findByName(system.getTitle(), System.class)) {
                system.setTitle(validation.firstCharToUpperCase(system.getTitle()));
                if (system.getId() == null) {
                    detailsDAO.manipulationData(system, "save");
                } else {
                    detailsDAO.manipulationData(system, "update");
                }
            }
        }
        system = new System();
        list();
    }

    @Override
    public void remove(System entity) {
        detailsDAO.manipulationData(entity, "remove");
        system = new System();
        list();
    }

    @Override
    public void edit(System entity) {
        this.system = entity;
    }

    @Override
    public void list() {
        systemList = detailsDAO.listAll(System.class);
    }
}
