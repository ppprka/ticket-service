package ru.innowise.danko.apigateway.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innowise.danko.apigateway.dto.UserDto;
import ru.innowise.danko.apigateway.dto.UserDto.UserDtoBuilder;
import ru.innowise.danko.apigateway.dto.UserRoleDto;
import ru.innowise.danko.apigateway.entity.User;
import ru.innowise.danko.apigateway.entity.User.UserBuilder;
import ru.innowise.danko.apigateway.entity.UserRole;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-21T18:51:20+0300",
    comments = "version: 1.5.0.Beta1, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.3.jar, environment: Java 15.0.2 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        if ( entity.getId() != null ) {
            userDto.id( entity.getId() );
        }
        if ( entity.getName() != null ) {
            userDto.name( entity.getName() );
        }
        if ( entity.getSurname() != null ) {
            userDto.surname( entity.getSurname() );
        }
        if ( entity.getUsername() != null ) {
            userDto.username( entity.getUsername() );
        }
        if ( entity.getPassword() != null ) {
            userDto.password( entity.getPassword() );
        }
        if ( entity.getEmail() != null ) {
            userDto.email( entity.getEmail() );
        }
        if ( entity.getFileId() != null ) {
            userDto.fileId( entity.getFileId() );
        }
        List<UserRoleDto> list = userRoleListToUserRoleDtoList( entity.getUserRoleList() );
        if ( list != null ) {
            userDto.userRoleList( list );
        }

        return userDto.build();
    }

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        if ( dto.getId() != null ) {
            user.id( dto.getId() );
        }
        if ( dto.getName() != null ) {
            user.name( dto.getName() );
        }
        if ( dto.getSurname() != null ) {
            user.surname( dto.getSurname() );
        }
        if ( dto.getUsername() != null ) {
            user.username( dto.getUsername() );
        }
        if ( dto.getPassword() != null ) {
            user.password( dto.getPassword() );
        }
        if ( dto.getEmail() != null ) {
            user.email( dto.getEmail() );
        }
        if ( dto.getFileId() != null ) {
            user.fileId( dto.getFileId() );
        }
        List<UserRole> list = userRoleDtoListToUserRoleList( dto.getUserRoleList() );
        if ( list != null ) {
            user.userRoleList( list );
        }

        return user.build();
    }

    protected List<UserRoleDto> userRoleListToUserRoleDtoList(List<UserRole> list) {
        if ( list == null ) {
            return null;
        }

        List<UserRoleDto> list1 = new ArrayList<UserRoleDto>( list.size() );
        for ( UserRole userRole : list ) {
            list1.add( userRoleMapper.toDto( userRole ) );
        }

        return list1;
    }

    protected List<UserRole> userRoleDtoListToUserRoleList(List<UserRoleDto> list) {
        if ( list == null ) {
            return null;
        }

        List<UserRole> list1 = new ArrayList<UserRole>( list.size() );
        for ( UserRoleDto userRoleDto : list ) {
            list1.add( userRoleMapper.toEntity( userRoleDto ) );
        }

        return list1;
    }
}
