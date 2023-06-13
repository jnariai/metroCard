package com.jnariai.user;

import com.jnariai.exceptions.EmailAlreadyExistException;
import com.jnariai.exceptions.RecordNotFoundException;
import com.jnariai.user.dto.CreateUserDto;
import com.jnariai.user.dto.UserDTO;
import com.jnariai.user.dto.UserMapper;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
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
                .map(userMapper::toListUserDto)
                .collect(Collectors.toList());
    }

    public UserDTO findById(String id) {
        return userRepository.findById(id).map(userMapper::toListUserDto)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public UserDTO create(@Valid @NotNull CreateUserDto userDto) {
        boolean emailExist = userRepository.findByEmail(userDto.email()).isPresent();
        if (emailExist){
            throw new EmailAlreadyExistException();
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return userMapper.toListUserDto(userRepository.save(userMapper.toEntityUser(userDto)));

    }
}
