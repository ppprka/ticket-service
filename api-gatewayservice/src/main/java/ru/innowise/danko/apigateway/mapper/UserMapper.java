package ru.innowise.danko.apigateway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import ru.innowise.danko.apigateway.dto.UserDto;
import ru.innowise.danko.apigateway.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserRoleMapper.class})
public interface UserMapper extends GenericMapper<User, UserDto> {

    @Override
    UserDto toDto(User entity);

    @Override
    User toEntity(UserDto dto);
}
