package ru.innowise.danko.apigateway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import ru.innowise.danko.apigateway.dto.UserRoleDto;
import ru.innowise.danko.apigateway.entity.UserRole;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserMapper.class})
public interface UserRoleMapper extends GenericMapper<UserRole, UserRoleDto> {

    @Override
    UserRoleDto toDto(UserRole entity);

    @Override
    UserRole toEntity(UserRoleDto dto);
}
