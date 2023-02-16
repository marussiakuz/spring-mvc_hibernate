package web.repository;

import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAll(int pageNumber, int pageSize) {
        return em.createQuery("from User", User.class)
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public Long getTotalCount() {
        return em.createQuery("select count(u) from User u", Long.class)
                .getSingleResult();
    }

    @Override
    public User create(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> getById(long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    public void delete(long id) {
        em.remove(em.find(User.class, id));
    }
}
