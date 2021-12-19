package ru.innowise.danko.apigateway.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.StringJoiner;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles", schema = "ticket_service")
public class UserRole{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_description")
    private String roleDescription;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "roles_users",
            joinColumns = { @JoinColumn(name = "id_role")},
            inverseJoinColumns = {@JoinColumn(name = "id_user")})
    private List<User> userList;

}
