package com.jnariai.user.dto;

import com.jnariai.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public UserDTO userToUserDTO(User user) {
    if (user == null) {
      return null;
    }

    return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassengerType());
  }

  public User createUserDTOToUser(CreateUserDto createUserDto) {
    if (createUserDto == null) {
      return null;
    }

    User user = new User();
    user.setName(createUserDto.name());
    user.setEmail(createUserDto.email());
    user.setPassword(createUserDto.password());
    user.setPassengerType(createUserDto.passengerType());
    System.out.println(createUserDto.passengerType());
    return user;
  }
}