package web.service;

import org.mapstruct.*;
import web.model.User;
import web.model.UserInDto;
import web.model.UserOutDto;

import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserInDto userInDto);

    UserOutDto toDto(User user);

    List<UserOutDto> toDto(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserInDto userInDto, @MappingTarget User user);

    static Date map(Long value) {
        return isNull(value) ? null : new Date(value);
    }
}
