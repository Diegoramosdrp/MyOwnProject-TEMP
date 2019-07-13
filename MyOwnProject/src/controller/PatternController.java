package controller;

import DAO.DetailsDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Pattern;
import util.ControllerInterface;
import util.Validation;

@ManagedBean(name = "PatternController")
@SessionScoped
public class PatternController implements ControllerInterface<Pattern> {
    
    private Pattern pattern = new Pattern();
    private final DetailsDAO<Pattern> detailsDAO = new DetailsDAO<>();
    private ArrayList<Pattern> patternList;
    public final Validation validation = new Validation();

    public PatternController() {
        this.patternList = detailsDAO.listAll(Pattern.class);
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public ArrayList<Pattern> getPatternList() {
        return patternList;
    }

    public void setPatternList(ArrayList<Pattern> patternList) {
        this.patternList = patternList;
    }

    @Override
    public void save() {
        if (!validation.isNULL(pattern.getTitle())) {
            if (!detailsDAO.findByName(pattern.getTitle(), Pattern.class)) {
                pattern.setTitle(validation.firstCharToUpperCase(pattern.getTitle()));
                if (pattern.getId() == null) {
                    detailsDAO.manipulationData(pattern, "save");
                } else {
                    detailsDAO.manipulationData(pattern, "update");
                }
            }
        }
        pattern = new Pattern();
        list();
    }

    @Override
    public void remove(Pattern entity) {
        detailsDAO.manipulationData(entity, "remove");
        pattern = new Pattern();
        list();
    }

    @Override
    public void edit(Pattern entity) {
        this.pattern = entity;
    }

    @Override
    public void list() {
        patternList = detailsDAO.listAll(Pattern.class);
    }
}
