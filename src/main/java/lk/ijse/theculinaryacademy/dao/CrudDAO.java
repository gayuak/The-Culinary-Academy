package lk.ijse.theculinaryacademy.dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    public List<T> getAll()  throws Exception ;
    public boolean add(T entity)  throws Exception;
    public boolean update(T entity)  throws Exception ;
    public T exist(String id)  throws Exception ;
    public boolean delete(Long id)  throws Exception ;


}
