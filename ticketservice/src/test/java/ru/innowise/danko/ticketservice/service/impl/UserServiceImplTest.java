package ru.innowise.danko.ticketservice.service.impl;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testng.annotations.Test;
import ru.innowise.danko.ticketservice.dto.UserDto;
import ru.innowise.danko.ticketservice.dto.UserRoleDto;
import ru.innowise.danko.ticketservice.entity.Flight;
import ru.innowise.danko.ticketservice.entity.Ticket;
import ru.innowise.danko.ticketservice.entity.User;
import ru.innowise.danko.ticketservice.entity.UserRole;
import ru.innowise.danko.ticketservice.mapper.UserMapper;
import ru.innowise.danko.ticketservice.mapper.UserRoleMapper;
import ru.innowise.danko.ticketservice.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


@Testcontainers
@SpringBootTest
class UserServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Test
    void addUser() {
        User expected = userProvider();
        UserDto actual = userService.addUser(userMapper.userToUserDto(expected));
        assertEquals(expected.getEmail(),actual.getEmail());
    }

    @Test
    void addRole() {
        UserRole expected = userRoleProvide(userProvider());
        UserRoleDto actual = userService.addRole(userRoleMapper.userRoleToUserRoleDto(expected));
        assertEquals(expected.getRoleName(),actual.getRoleName());
    }

    @Test
    void addUserRoleToUser() {
        User user = userProvider();
        user.setUserRoleList(null);
        UserRole userRole = UserRole.builder()
                .roleName("pepe")
                .roleDescription("ga")
                .build();
        UserRoleDto expected = userService.addRole(userRoleMapper.userRoleToUserRoleDto(userRole));
        Long idRole = expected.getId();
        Long idUser = userService.addUser(userMapper.userToUserDto(user)).getId();
        List<String> roleNames= userService.addUserRoleToUser(idUser, idRole)
                .getUserRoleList().stream().map(UserRoleDto::getRoleName).collect(Collectors.toList());
        assertTrue(roleNames.contains(userRole.getRoleName()));


    }

    private User userProvider(){
            Flight flight = Flight.builder()
                    .arrivalAirport("AAA")
                    .arrivalTime(LocalDateTime.now())
                    .departureAirport("3333")
                    .flightNumber("2222")
                    .departureTime(LocalDateTime.now())
                    .build();
            User user = User.builder()
                    .email("google@gmail.com")
                    .name("2")
                    .username("3")
                    .password("4")
                    .surname("5")
                    .build();
        UserRole userRole = userRoleProvide(user);
        user.setUserRoleList(List.of(userRole));
            Ticket ticket = Ticket.builder()
                    .classType("type")
                    .flight(flight)
                    .gate("gate")
                    .seat("seat")
                    .user(user)
                    .ticketNumber("number")
                    .user(new User()).build();
            Set<Ticket> ticketSet = Set.of(ticket);
            flight.setTicketSet(ticketSet);
            ticket.setFlight(flight);
            user.setTicketSet(ticketSet);
            return user;
    }

    private UserRole userRoleProvide(User user){
        return UserRole.builder()
                .roleName("user")
                .roleDescription("krutaya")
                .userList(List.of(user))
                .build();
    }
}