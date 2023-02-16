package web.repository;

import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getAll(int pageNumber, int pageSize);

    Long getTotalCount();

    User create(User user);

    Optional<User> getById(long id);

    User update(User user);

    void delete(long id);
}
