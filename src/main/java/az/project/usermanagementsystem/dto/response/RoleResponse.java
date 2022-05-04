package az.project.usermanagementsystem.dto.response;

import az.project.usermanagementsystem.dao.entity.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {
    private Long id;
    private ERole roleName;
}
