package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.exception.UserNotFoundException;
import web.model.User;
import web.model.UserInDto;
import web.model.UserOutDto;
import web.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserOutDto> getAll(int pageNumber, int pageSize) {
        return userMapper.toDto(userRepository.getAll(pageNumber, pageSize));
    }

    @Transactional(readOnly = true)
    @Override
    public Long getTotalCount() {
        return userRepository.getTotalCount();
    }

    @Transactional
    @Override
    public UserOutDto create(UserInDto user) {
        return userMapper.toDto(userRepository.create(userMapper.toUser(user)));
    }

    @Transactional
    @Override
    public UserOutDto update(long id, UserInDto userDto) {
        User user = userRepository.getById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("user with id=%d not found", id)));
        userMapper.updateUserFromDto(userDto, user);
        return userMapper.toDto(userRepository.update(user));
    }

    @Transactional
    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }
}
