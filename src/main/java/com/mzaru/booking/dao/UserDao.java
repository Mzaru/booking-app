package com.mzaru.booking.dao;

import com.mzaru.booking.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDao implements IUserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.save(user);
    }

    @Override
    public List getAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);
        query.multiselect(userRoot.get("id"), userRoot.get("name"), userRoot.get("surname"), userRoot.get("login"));
        return session.createQuery(query).getResultList();
    }

    @Override
    public void editUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.update(user);
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.bySimpleNaturalId(User.class).load(login);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(user);
    }
}
