package controller;

import DAO.DetailsDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Language;
import util.ControllerInterface;
import util.Validation;

@ManagedBean(name = "LanguageController")
@SessionScoped
public class LanguageController implements ControllerInterface<Language> {

    private Language language = new Language();
    private final DetailsDAO<Language> detailsDAO = new DetailsDAO<>();
    private ArrayList<Language> languageList;
    public final Validation validation = new Validation();

    public LanguageController() {
        this.languageList = detailsDAO.listAll(Language.class);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public ArrayList<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(ArrayList<Language> languageList) {
        this.languageList = languageList;
    }

    @Override
    public void save() {
        if (!validation.isNULL(language.getTitle())) {
            if (!detailsDAO.findByName(language.getTitle(), Language.class)) {
                language.setTitle(validation.firstCharToUpperCase(language.getTitle()));
                if (language.getId() == null) {
                    detailsDAO.manipulationData(language, "save");
                } else {
                    detailsDAO.manipulationData(language, "update");
                }
            }
        }
        language = new Language();
        list();
    }

    @Override
    public void remove(Language entity) {
        detailsDAO.manipulationData(entity, "remove");
        language = new Language();
        list();
    }

    @Override
    public void edit(Language entity) {
        this.language = entity;
    }

    @Override
    public void list() {
        languageList = detailsDAO.listAll(Language.class);
    }
}