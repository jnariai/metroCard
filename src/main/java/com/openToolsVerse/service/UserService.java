package com.template.template.service;

import com.template.template.dto.RequestUserDto;
import com.template.template.dto.ResponseUserDto;
import com.template.template.entity.User;
import com.template.template.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public List<ResponseUserDto> findAll(){
        List<User> users = this.userRepository.findAll();
        return users.stream().map((user) -> modelMapper.map(user, ResponseUserDto.class)).collect(Collectors.toList());

    }
    public ResponseUserDto save(RequestUserDto userDTO){
        User user = convertRequestUserDtoToUser(userDTO);
        this.userRepository.save(user);
        return convertUserToResponseUserDto(user);
    }

    private ResponseUserDto convertUserToResponseUserDto(User user){
        return modelMapper.map(user, ResponseUserDto.class);
    }
    private User convertRequestUserDtoToUser(RequestUserDto userDTO){
        return modelMapper.map(userDTO, User.class);
    }
}
