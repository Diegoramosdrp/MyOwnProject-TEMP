package DAO;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Status;
import org.hibernate.AnnotationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.DAOInterface;
import util.HibernateUtil;
import util.Validation;

public class DetailsDAO<Entity> implements DAOInterface<Entity> {

    private Session session = null;
    private Transaction transaction = null;
    private Validation validation = new Validation();

    @Override
    public void manipulationData(Entity entity, String method) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        try {
            switch (method) {
                case "save":
                    session.save(entity);
                    validation.addMessage("Adicionado com sucesso", FacesMessage.SEVERITY_INFO);
                    break;

                case "update":
                    session.update(entity);
                    validation.addMessage("Atualizado com sucesso", FacesMessage.SEVERITY_INFO);
                    break;

                case "remove":
                    session.remove(entity);
                    validation.addMessage("Removido com sucesso", FacesMessage.SEVERITY_INFO);
                    break;

                default:
                    HibernateException he = new AnnotationException("Invalid Method");
                    validation.addMessage("ERROR", FacesMessage.SEVERITY_FATAL);
            }
            transaction.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            validation.addMessage("Nao foi possivel executar com sucesso o processo" + " : " + method.toUpperCase(), FacesMessage.SEVERITY_FATAL);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean findByName(String name, Class clazz) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        ArrayList<Entity> list = new ArrayList<>();
        Entity entity = null;

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Entity> query = builder.createQuery(clazz);
            Root<Entity> root = query.from(clazz);
            query.select(root);
            query.where(builder.equal(root.get("Title"), name));
            list = (ArrayList<Entity>) session.createQuery(query).getResultList();
            transaction.commit();

            if (!list.isEmpty()) {
                validation.addMessage("Já cadastrado", FacesMessage.SEVERITY_FATAL);
                return true;
            } else {
                return false;
            }
        } catch (HibernateException he) {
            he.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public ArrayList<Entity> listAll(Class clazz) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        ArrayList<Entity> detailsList = new ArrayList<>();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Entity> query = builder.createQuery(clazz);
            Root<Entity> root = query.from(clazz);
            query.select(root);
            Query<Entity> q = session.createQuery(query);
            detailsList = (ArrayList) q.getResultList();
            transaction.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return detailsList;
    }
    
    public boolean findByColor(String color){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        ArrayList<Status> list = new ArrayList<>();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Status> query = builder.createQuery(Status.class);
            Root<Status> root = query.from(Status.class);
            query.select(root);
            query.where(builder.equal(root.get("BackgroundColor"), color));
            list = (ArrayList<Status>) session.createQuery(query).getResultList();
            transaction.commit();

            if (!list.isEmpty()) {
                validation.addMessage("Cor já definido para outro status", FacesMessage.SEVERITY_FATAL);
                return true;
            } else {
                return false;
            }
        } catch (HibernateException he) {
            he.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }
}

