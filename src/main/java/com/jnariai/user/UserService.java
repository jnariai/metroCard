package com.jnariai.user;

import com.jnariai.user.dto.CreateUserDto;
import com.jnariai.user.dto.UserDTO;
import com.jnariai.user.dto.UserMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::userToUserDTO).toList();
    }

    public UserDTO findById(String id) {
        return userRepository.findById(id).map(userMapper::userToUserDTO).orElseThrow(EntityNotFoundException::new);
    }

    public UserDTO createUser(CreateUserDto userDto) {
        boolean emailExist = userRepository.existsByEmail(userDto.email());
        if (emailExist) {
            throw new EntityExistsException();
        }
        User user = userMapper.createUserDTOToUser(userDto);
        User createdUser = userRepository.save(user);
        return userMapper.userToUserDTO(createdUser);

    }
}
