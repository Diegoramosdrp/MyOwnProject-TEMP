package util;

import java.util.ArrayList;

public abstract interface DAOInterface<E> {
    public void manipulationData(E entity, String method);
    public ArrayList<E> listAll(Class clazz);
    public boolean findByName(String name, Class clazz);
}
