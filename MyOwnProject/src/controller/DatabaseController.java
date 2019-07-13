package controller;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Database;
import DAO.DetailsDAO;
import util.ControllerInterface;
import util.Validation;

@ManagedBean(name = "DatabaseController")
@SessionScoped
public class DatabaseController implements ControllerInterface<Database> {

    private Database database = new Database();
    private final DetailsDAO<Database> detailsDAO = new DetailsDAO<>();
    private ArrayList<Database> databaseList;
    public final Validation validation = new Validation();

    public DatabaseController() {
        this.databaseList = detailsDAO.listAll(Database.class);
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public ArrayList<Database> getDatabaseList() {
        return databaseList;
    }

    public void setDatabaseList(ArrayList<Database> databaseList) {
        this.databaseList = databaseList;
    }

    @Override
    public void save() {
        if (!validation.isNULL(database.getTitle())) {
            if (!detailsDAO.findByName(database.getTitle(), Database.class)) {
                database.setTitle(validation.firstCharToUpperCase(database.getTitle()));
                if (database.getId() == null) {
                    detailsDAO.manipulationData(database, "save");
                } else {
                    detailsDAO.manipulationData(database, "update");
                }
            }
        }
        database = new Database();
        list();
    }

    @Override
    public void remove(Database entity) {
        detailsDAO.manipulationData(entity, "remove");
        database = new Database();
        list();
    }
    
    @Override
    public void edit(Database entity) {
        this.database = entity;
    }

    @Override
    public void list() {
        databaseList = detailsDAO.listAll(Database.class);
    }
}