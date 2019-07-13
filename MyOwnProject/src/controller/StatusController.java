package controller;

import DAO.DetailsDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Status;
import util.ControllerInterface;
import util.Validation;

@ManagedBean(name = "StatusController")
@SessionScoped
public class StatusController implements ControllerInterface<Status> {

    private Status status = new Status();
    private final DetailsDAO<Status> detailsDAO = new DetailsDAO<>();
    private ArrayList<Status> statusList;
    public final Validation validation = new Validation();
    private Status backup = new Status();

    public StatusController() {
        this.statusList = detailsDAO.listAll(Status.class);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(ArrayList<Status> statusList) {
        this.statusList = statusList;
    }

    @Override
    public void save() {
        if (!validation.isNULL(status)) {
            status.setTitle(validation.firstCharToUpperCase(status.getTitle()));
            status.setBackgroundColor(validation.formatColor(status.getBackgroundColor()));
            status.setFontColor(validation.defineTextColor(status.getBackgroundColor()));
            if (status.getId() == null) {
                if (!detailsDAO.findByName(status.getTitle(), Status.class)) {
                    if (!detailsDAO.findByColor(status.getBackgroundColor())) {
                        detailsDAO.manipulationData(status, "save");
                    }
                }
            } else {
                if (status.getTitle().equals(backup.getTitle())) {
                    if (!detailsDAO.findByColor(status.getBackgroundColor())) {
                        detailsDAO.manipulationData(status, "update");
                    }
                }

                if (status.getBackgroundColor().equals(backup.getBackgroundColor())) {
                    if (!detailsDAO.findByName(status.getTitle(), Status.class)) {
                        detailsDAO.manipulationData(status, "update");
                    }
                }

                if (!status.getTitle().equals(backup.getTitle()) && !status.getBackgroundColor().equals(backup.getBackgroundColor())) {
                    if (!detailsDAO.findByName(status.getTitle(), Status.class) && !detailsDAO.findByColor(status.getBackgroundColor())) {
                        detailsDAO.manipulationData(status, "update");
                    }
                }
            }
        }
        status = new Status();
        list();
    }

    @Override
    public void remove(Status entity) {
        detailsDAO.manipulationData(entity, "remove");
        status = new Status();
        list();
    }

    @Override
    public void edit(Status entity) {
        this.status = entity;
        this.backup.setTitle(entity.getTitle());
        this.backup.setBackgroundColor(entity.getBackgroundColor());
    }

    @Override
    public void list() {
        statusList = detailsDAO.listAll(Status.class);
    }
}
