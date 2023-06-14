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

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> list() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findById(String id) {
        return userRepository.findById(id).map(userMapper::userToUserDTO)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public ResponseEntity<UserDTO> createUser(@Valid @NotNull CreateUserDto userDto) {
        boolean emailExist = userRepository.existsByEmail(userDto.email());
        if (emailExist){
            throw new EntityExistsException();
        }
        User user = userMapper.createUserDTOToUser(userDto);
        User newUser = userRepository.save(user);
        return ResponseEntity.created(null).body(userMapper.userToUserDTO(newUser));

    }
}
