package com.jnariai.user;

import com.jnariai.user.dto.CreateUserDto;
import com.jnariai.user.dto.UserDTO;
import com.jnariai.user.dto.UserMapper;
import com.jnariai.user.dto.UserPasswordDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        user.setPassword(hashPassword(user.getPassword()));
        User createdUser = userRepository.save(user);
        return userMapper.userToUserDTO(createdUser);
    }

    public String hashPassword(String plainPassword) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        return bcrypt.encode(plainPassword);
    }

    public void updateUserPassword(String id, UserPasswordDTO userPasswordDTO) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        if (bcrypt.matches(userPasswordDTO.oldPassword(), user.getPassword())) {
            user.setPassword(this.hashPassword(userPasswordDTO.newPassword()));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    }
}
