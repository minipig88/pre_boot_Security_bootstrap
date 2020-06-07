package com.example.pre_3_1_2.dao;

import com.example.pre_3_1_2.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class RoleDaoImpl implements RoleDao {

    private final EntityManager entityManager;

    @Autowired
    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void update(Role role) {
        entityManager.merge(role);
    }

    @Override
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role findByName(String name) {
        Query query = entityManager.createQuery("from Role where roleName = :name");
        query.setParameter("name", name);
        return (Role) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> findAll() {
        TypedQuery<Role> query = (TypedQuery<Role>) entityManager.createQuery("from Role");
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Role where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
