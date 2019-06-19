/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coquetails.coquetails.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author bibil
 */
public class ManagerDao {

    private static ManagerDao managerDao;
    private EntityManagerFactory emf = null;

    public static ManagerDao getCurrentInstance() {
        if (managerDao == null) {
            managerDao = new ManagerDao();
        }
        return managerDao;
    }

    private ManagerDao() {
        this.emf = Persistence.createEntityManagerFactory("CoquetailsPU");
    }

    public void insert(Object o) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(o);
        em.flush();
        em.getTransaction().commit();
        em.close();

    }

    public void update(Object o) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(o);
        em.flush();
        em.getTransaction().commit();
        em.close();

    }

    public void delete(Object o) {
        EntityManager em = emf.createEntityManager();
        Object object = o;
        if (!em.contains(o)) {
            object = em.merge(o);
        }
        em.getTransaction().begin();
        em.remove(object);
        em.getTransaction().commit();
        em.clear();
        em.close();

    }

    public List read(String query, Class c) {
        EntityManager em = emf.createEntityManager();
        List returnedList = em.createQuery(query, c).getResultList();
        em.close();
        return returnedList;

    }

}
