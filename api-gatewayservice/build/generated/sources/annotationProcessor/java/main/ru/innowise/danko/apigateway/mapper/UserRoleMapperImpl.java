package ru.innowise.danko.apigateway.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.innowise.danko.apigateway.dto.UserRoleDto;
import ru.innowise.danko.apigateway.dto.UserRoleDto.UserRoleDtoBuilder;
import ru.innowise.danko.apigateway.entity.UserRole;
import ru.innowise.danko.apigateway.entity.UserRole.UserRoleBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-21T18:51:20+0300",
    comments = "version: 1.5.0.Beta1, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.3.jar, environment: Java 15.0.2 (Amazon.com Inc.)"
)
@Component
public class UserRoleMapperImpl implements UserRoleMapper {

    @Override
    public UserRoleDto toDto(UserRole entity) {
        if ( entity == null ) {
            return null;
        }

        UserRoleDtoBuilder userRoleDto = UserRoleDto.builder();

        if ( entity.getId() != null ) {
            userRoleDto.id( entity.getId() );
        }
        if ( entity.getRoleName() != null ) {
            userRoleDto.roleName( entity.getRoleName() );
        }
        if ( entity.getRoleDescription() != null ) {
            userRoleDto.roleDescription( entity.getRoleDescription() );
        }

        return userRoleDto.build();
    }

    @Override
    public UserRole toEntity(UserRoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserRoleBuilder userRole = UserRole.builder();

        if ( dto.getId() != null ) {
            userRole.id( dto.getId() );
        }
        if ( dto.getRoleName() != null ) {
            userRole.roleName( dto.getRoleName() );
        }
        if ( dto.getRoleDescription() != null ) {
            userRole.roleDescription( dto.getRoleDescription() );
        }

        return userRole.build();
    }
}
