/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.EntityBase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author erick.costa
 * @param <T>
 */
public class DaoGenerico<T extends EntityBase> {

    private static EntityManager manager = ConnectionFactory.getEntityManager();

    public T findById(Class<T> clazz, Integer id) {
        return manager.find(clazz, id);
    }

    public T saveOrUpdate(T obj) {
        try {
            manager.getTransaction().begin();
            if (obj.getId() == null) {
                manager.persist(obj);
            } else {
                manager.merge(obj);
            }
            manager.getTransaction().commit();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
            System.out.println("Não foi possível realizar o Cadastro ou Alteração.");
            return null;
        }
    }

    public void remove(Class<T> clazz, Integer id) {
        T t = findById(clazz, id);
        try {
            manager.getTransaction().begin();
            manager.remove(t);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }

    public List<T> getList(Class<T> clazz) {
        if (clazz.getName().equalsIgnoreCase("entities.Fornecedor") 
                | clazz.getName().equalsIgnoreCase("entities.Usuario") 
                | clazz.getName().equalsIgnoreCase("entities.Cliente")
                | clazz.getName().equalsIgnoreCase("entities.Produto")) {
            TypedQuery<T> q = manager.createQuery("select t from " + clazz.getName() + " t where t.ativo=1", clazz);
            return q.getResultList();
        }
        TypedQuery<T> q = manager.createQuery("select t from " + clazz.getName() + " t", clazz);
        return q.getResultList();
    }
}
