package com.jnariai.user;

import com.jnariai.user.dto.CreateUserDto;
import com.jnariai.user.dto.UserDTO;
import com.jnariai.user.dto.UserMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> list() {
        return userRepository.findAll().stream().map(userMapper::userToUserDTO).toList();
    }

    public UserDTO findById(String id) {
        return userRepository.findById(id).map(userMapper::userToUserDTO).orElseThrow(EntityNotFoundException::new);
    }

    public ResponseEntity<UserDTO> createUser(@Valid @NotNull CreateUserDto userDto) {
        boolean emailExist = userRepository.existsByEmail(userDto.email());
        if (emailExist) {
            throw new EntityExistsException();
        }
        User user = userMapper.createUserDTOToUser(userDto);
        User createdUser = userRepository.save(user);
        URI location = URI.create("/api/users/" + createdUser.getId());
        return ResponseEntity.created(location).body(userMapper.userToUserDTO(createdUser));

    }
}
