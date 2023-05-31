package com.jnariai.dto.mapper;

import org.springframework.stereotype.Component;

import com.jnariai.dto.CreateUserDto;
import com.jnariai.dto.ListUserDto;
import com.jnariai.entity.User;
import com.jnariai.shared.PassagerType;

@Component
public class UserMapper {
  public ListUserDto toListUserDto (User user) {
    if (user == null) {
      return null;
    }

    return new ListUserDto(user.getId(), user.getName(), user.getEmail(), user.getPassagerType());
  }

  public User toEntityUser(CreateUserDto createUserDto){
    if (createUserDto == null){
      return null;
    }

    User user = new User();
    user.setName(createUserDto.name());
    user.setEmail(createUserDto.email());
    user.setPassword(createUserDto.password());
    user.setPassagerType(this.convertToPassagerType(createUserDto.passagerType()));
    return user;
  }

  public PassagerType convertToPassagerType(String passagerType){
    if (passagerType == null){
      return null;
    }
    return switch (passagerType) {
      case "ADULT" -> PassagerType.ADULT;
      case "KID" -> PassagerType.KID;
      case "SENIOR" -> PassagerType.SENIOR;
      default -> throw new IllegalArgumentException("Passager type is invalid: " + passagerType);
    };
  }

}