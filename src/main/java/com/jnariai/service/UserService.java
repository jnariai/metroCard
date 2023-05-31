package com.jnariai.service;

import com.jnariai.dto.CreateUserDto;
import com.jnariai.dto.ListUserDto;
import com.jnariai.dto.mapper.UserMapper;
import com.jnariai.exceptions.EmailAlreadyExistException;
import com.jnariai.exceptions.RecordNotFoundException;
import com.jnariai.repository.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<ListUserDto> list() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toListUserDto)
                .collect(Collectors.toList());
    }

    public ListUserDto findById(String id) {
        return userRepository.findById(id).map(userMapper::toListUserDto)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public ListUserDto create(@Valid @NotNull CreateUserDto userDto) {
        boolean emailExist = userRepository.findByEmail(userDto.email()).isPresent();
        if (emailExist){
            throw new EmailAlreadyExistException();
        }
        return userMapper.toListUserDto(userRepository.save(userMapper.toEntityUser(userDto)));

    }
}
