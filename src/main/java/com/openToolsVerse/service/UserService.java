package com.openToolsVerse.service;

import com.openToolsVerse.dto.RequestUserDto;
import com.openToolsVerse.dto.ResponseUserDto;
import com.openToolsVerse.entity.User;
import com.openToolsVerse.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<ResponseUserDto> findAll() {
        return this.userRepository.findAll().stream().map(this::mapUserToResponseUserDto).collect(Collectors.toList());
    }

    public ResponseUserDto save(RequestUserDto userDTO) {
        // validate email
        User user = mapRequestUserDtoToUser(userDTO);
        this.userRepository.save(user);
        return mapUserToResponseUserDto(user);
    }

    public ResponseUserDto update(RequestUserDto requestUserDto, String id) {
        User user = this.findById(id);
        if (requestUserDto.name() != null) {
            user.setName(requestUserDto.name());
        }
        if (requestUserDto.email() != null) {
            user.setEmail(requestUserDto.email());
        }
        if (requestUserDto.password() != null) {
            user.setPassword(requestUserDto.password());
        }
        if (requestUserDto.passagerType() != null) {
            user.setPassagerType(requestUserDto.passagerType());
        }
        this.userRepository.save(user);
        return mapUserToResponseUserDto(user);
    }

    public void delete(String id){
        User user = this.findById(id);
        if  (user != null) this.userRepository.deleteById(id);

    }

    public User findById(String id) {
        return this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User does not exist"));
    }

    private ResponseUserDto mapUserToResponseUserDto(User user) {
        return new ResponseUserDto(user.getId(), user.getName(), user.getEmail(), user.getPassagerType());
    }

    private User mapRequestUserDtoToUser(RequestUserDto userDTO) {
        return new User(userDTO.name(), userDTO.email(), userDTO.password(), userDTO.passagerType());
    }
}
