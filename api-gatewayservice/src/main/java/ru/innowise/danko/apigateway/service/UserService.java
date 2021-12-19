package ru.innowise.danko.apigateway.service;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innowise.danko.apigateway.dto.UserDto;
import ru.innowise.danko.apigateway.dto.UserRoleDto;
import ru.innowise.danko.apigateway.entity.User;
import ru.innowise.danko.apigateway.entity.UserRole;
import ru.innowise.danko.apigateway.mapper.UserMapper;
import ru.innowise.danko.apigateway.mapper.UserRoleMapper;
import ru.innowise.danko.apigateway.repository.UserRepository;
import ru.innowise.danko.apigateway.repository.UserRoleRepository;
import ru.innowise.danko.apigateway.util.ObjectNotFound;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository,
                       UserMapper userMapper, UserRoleMapper userRoleMapper,
                       BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.encoder = encoder;
    }

    public UserDto register(@NotNull UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        return userMapper.toDto(userRepository
                .save(userMapper.toEntity(userDto)));
    }

    public UserRoleDto addRole(@NotNull UserRoleDto roleDto) {
        return userRoleMapper.toDto(userRoleRepository
                .save(userRoleMapper.toEntity(roleDto)));
    }

    public UserDto addUserRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFound(User.class,userId));
        UserRole role = userRoleRepository.findById(roleId)
                .orElseThrow(() -> new ObjectNotFound(UserRole.class,roleId));
        role.getUserList().add(user);
        user.getUserRoleList().add(role);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(userMapper::toDto)
                .orElseThrow(() -> new ObjectNotFound(User.class,username));
    }

    public Long getUserIdByUsername(String username){
        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElseThrow(() -> new ObjectNotFound(User.class,username));
    }

    public UserDto getUserById(Long id){
        return userMapper.toDto(userRepository
                    .findById(id)
                    .orElseThrow(() -> new ObjectNotFound(User.class, id)));
    }

}
