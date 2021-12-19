package ru.innowise.danko.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleDto implements GrantedAuthority{
    private Long id;
    private String roleName;
    private String roleDescription;
    private List<UserDto> userSet;

    @Override
    public String getAuthority() {
        return getRoleName();
    }
}
