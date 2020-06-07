package com.example.pre_3_1_2.dao;

import com.example.pre_3_1_2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public User findByName(String name) {
        Query query = entityManager.createQuery("from User where username = :name");
        query.setParameter("name", name);
        return (User) query.getSingleResult();
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        TypedQuery<User> query = (TypedQuery<User>) entityManager.createQuery("from User");
        return query.getResultList();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from User where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }
}
