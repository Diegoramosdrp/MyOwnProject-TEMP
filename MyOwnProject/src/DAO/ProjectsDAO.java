package DAO;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Link;
import model.Note;
import model.Problem;
import model.Project;
import model.Status;
import model.Task;

import org.hibernate.AnnotationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import util.DAOInterface;
import util.HibernateUtil;
import util.Validation;

public class ProjectsDAO<Entity> implements DAOInterface<Project> {

    private Session session = null;
    private Transaction transaction = null;
    private Validation validation = new Validation();

    @Override
    public void manipulationData(Project entity, String method) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        try {
            switch (method) {
                case "saveProject":
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
    
    public void manipulationData(Task task, String method) {
    	session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        try {
            switch (method) {
                case "saveTask":
                    session.save(task);
                    validation.addMessage("Tarefa adicionado com sucesso", FacesMessage.SEVERITY_INFO);
                    break;

                case "update":
                    session.update(task);
                    
                    if (task.isFinished()) {
                    	validation.addMessage("Tarefa finalizado com sucesso", FacesMessage.SEVERITY_INFO);
					} else {
						validation.addMessage("Tarefa atualizado com sucesso", FacesMessage.SEVERITY_INFO);
					}
                    break;

                case "remove":
                    session.remove(task);
                    validation.addMessage("Tarefa removido com sucesso", FacesMessage.SEVERITY_INFO);
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
    
    public void manipulationData(Problem problem, String method) {
    	session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        try {
            switch (method) {
                case "saveProblem":
                    session.save(problem);
                    validation.addMessage("Problema adicionado com sucesso", FacesMessage.SEVERITY_INFO);
                    break;

                case "update":
                    session.update(problem);
                    
                    if (problem.isSolved()) {
                    	validation.addMessage("Problema resolvido", FacesMessage.SEVERITY_INFO);
					} else {
						validation.addMessage("Problema atualizado com sucesso", FacesMessage.SEVERITY_INFO);
					}
                    break;

                case "remove":
                    session.remove(problem);
                    validation.addMessage("Problema removido com sucesso", FacesMessage.SEVERITY_INFO);
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
    
    public void manipulationData(Note note, String method) {
    	session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        try {
            switch (method) {
                case "saveNote":
                    session.save(note);
                    validation.addMessage("Nota adicionada com sucesso", FacesMessage.SEVERITY_INFO);
                    break;

                case "update":
                    session.update(note);
                    validation.addMessage("Nota atualizada com sucesso", FacesMessage.SEVERITY_INFO);
                    break;

                case "remove":
                    session.remove(note);
                    validation.addMessage("Nota removida com sucesso", FacesMessage.SEVERITY_INFO);
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
    
    public void manipulationData(Link link, String method) {
    	session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        try {
            switch (method) {
                case "saveLink":
                    session.save(link);
                    validation.addMessage("Link adicionado com sucesso", FacesMessage.SEVERITY_INFO);
                    break;

                case "update":
                    session.update(link);
                    validation.addMessage("Link atualizado com sucesso", FacesMessage.SEVERITY_INFO);
                    break;

                case "remove":
                    session.remove(link);
                    validation.addMessage("Link removida com sucesso", FacesMessage.SEVERITY_INFO);
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
    public ArrayList<Project> listAll(Class clazz) {
    	session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        ArrayList<Project> projectList = new ArrayList<>();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Entity> query = builder.createQuery(clazz);
            Root<Entity> root = query.from(clazz);
            query.select(root);
            Query<Entity> q = session.createQuery(query);
            projectList = (ArrayList) q.getResultList();
            transaction.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return projectList;
    }

    @Override
    public boolean findByName(String name, Class clazz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Project findById(Integer id) {
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();
        Project project = new Project();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Project> query = builder.createQuery(Project.class);
            Root<Project> root = query.from(Project.class);
            query.select(root);
            query.where(builder.equal(root.get("Id"), id));
            project = session.createQuery(query).getSingleResult();
            
            transaction.commit();
            
            return project;
            
        } catch (HibernateException he) {
            he.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        
        return project;
    }
    
    public ArrayList<Project> currentsProjects(Class clazz, Integer statusId){
    	session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        ArrayList<Project> projectList = new ArrayList<>();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Entity> query = builder.createQuery(clazz);
            Root<Entity> root = query.from(clazz);
            query.select(root);
            query.where(builder.notEqual(root.get("status"), statusId));
            Query<Entity> q = session.createQuery(query);
            projectList = (ArrayList) q.getResultList();
            transaction.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return projectList;
    }
    
    public Status getStatusDefault(String parameter) {
    	session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Status status = new Status();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Status> query = builder.createQuery(Status.class);
            Root<Status> root = query.from(Status.class);
            query.select(root);
            query.where(builder.equal(root.get("Title"), parameter));
            status = session.createQuery(query).getSingleResult();
            transaction.commit();
            
            return status;
            
        } catch (HibernateException he) {
            he.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        
        return status;
    }
}
