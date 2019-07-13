package converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.Session;

import model.Framework;
import util.HibernateUtil;

@FacesConverter("frameworkConverter")
public class FrameworkConverter implements Converter, Serializable{

	private static final long serialVersionUID = 1L;
	private Session session = null;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
		
		if (id != null && !id.isEmpty()) {
			session = HibernateUtil.getSessionFactory().openSession();
			Framework framework;
			framework = (Framework) session.get(Framework.class, new Integer(id));
			session.close();
			return framework;
		}
		return id;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		
		if (object != null) {
			Framework framework = (Framework) object;
			return framework.getId().toString();
		}
		return null;
	}
}
