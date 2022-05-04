package az.project.usermanagementsystem.service;

import az.project.usermanagementsystem.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto getById(Long id);

    void update(Long id, UserDto dto);

    void delete(Long id);
}
