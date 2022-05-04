package az.project.usermanagementsystem.service;

import az.project.usermanagementsystem.dao.entity.RoleEntity;
import az.project.usermanagementsystem.dao.entity.UserEntity;
import az.project.usermanagementsystem.dao.repository.UserRepository;
import az.project.usermanagementsystem.dto.response.RoleResponse;
import az.project.usermanagementsystem.dto.UserDto;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAll() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(userEntity -> UserDto.builder()
                        .id(userEntity.getId())
                        .firstName(userEntity.getFirstName())
                        .lastName(userEntity.getLastName())
                        .username(userEntity.getUsername())
                        .email(userEntity.getEmail())
                        .roles(userEntity.getRoles().stream()
                                .map(role -> new RoleResponse(role.getId(), role.getName()))
                                .collect(Collectors.toSet()))
                        .build()).collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        UserEntity userEntity = userRepository.getById(id);
        return UserDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoles().stream()
                        .map(role -> new RoleResponse(role.getId(), role.getName()))
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public void update(Long id, UserDto dto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
        userEntity.setId(dto.getId());
        userEntity.setFirstName((dto.getFirstName()));
        userEntity.setLastName((dto.getLastName()));
        userEntity.setUsername(dto.getUsername());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPassword(dto.getPassword());
        userEntity.setRoles(dto.getRoles().stream()
                .map(role -> new RoleEntity(role.getId(), role.getRoleName()))
                .collect(Collectors.toSet()));
        userRepository.save(userEntity);
    }

    @Override
    public void delete(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
        user.setRoles(new HashSet());
        userRepository.save(user);
        userRepository.delete(user);
    }
}