package web.service;

import web.model.UserInDto;
import web.model.UserOutDto;

import java.util.List;

public interface UserService {

    List<UserOutDto> getAll(int pageNumber, int pageSize);

    Long getTotalCount();

    UserOutDto create(UserInDto user);

    UserOutDto update(long id, UserInDto user);

    void delete(long id);
}
