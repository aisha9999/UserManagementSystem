package az.project.usermanagementsystem.dto;

import az.project.usermanagementsystem.dto.response.RoleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<RoleResponse> roles;
}