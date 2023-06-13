package com.jnariai.user.dto;

import org.springframework.stereotype.Component;

import com.jnariai.shared.PassangerType;
import com.jnariai.user.User;

@Component
public class UserMapper {
  public UserDTO toListUserDto (User user) {
    if (user == null) {
      return null;
    }

    return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassangerType());
  }

  public User toEntityUser(CreateUserDto createUserDto){
    if (createUserDto == null){
      return null;
    }

    User user = new User();
    user.setName(createUserDto.name());
    user.setEmail(createUserDto.email());
    user.setPassword(createUserDto.password());
    user.setPassangerType(this.convertToPassangerType(createUserDto.passangerType()));
    return user;
  }

  public PassangerType convertToPassangerType(String passangerType){
    if (passangerType == null){
      return null;
    }
    return switch (passangerType) {
      case "ADULT" -> PassangerType.ADULT;
      case "KID" -> PassangerType.KID;
      case "SENIOR" -> PassangerType.SENIOR;
      default -> throw new IllegalArgumentException("Passanger type is invalid: " + passangerType);
    };
  }

}