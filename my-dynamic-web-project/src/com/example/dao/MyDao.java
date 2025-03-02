package com.example.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.example.model.MyModel;
import com.example.util.HibernateUtil;
import java.util.List;

public class MyDao {

    public void save(MyModel model) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(model);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(MyModel model) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(model);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(MyModel model) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(model);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public MyModel getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(MyModel.class, id);
        }
    }

    public List<MyModel> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MyModel> query = session.createQuery("from MyModel", MyModel.class);
            return query.list();
        }
    }

    public List<MyModel> getAll(int pageNumber, int pageSize, String filter) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from MyModel where name like :filter";
            Query<MyModel> query = session.createQuery(hql, MyModel.class);
            query.setParameter("filter", "%" + filter + "%");
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }
}