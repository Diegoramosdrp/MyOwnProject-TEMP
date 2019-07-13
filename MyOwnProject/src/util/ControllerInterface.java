package util;

public interface ControllerInterface<E> {
    public void save();
    public void remove(E entity);
    public void edit(E entity);
    public void list();
}
