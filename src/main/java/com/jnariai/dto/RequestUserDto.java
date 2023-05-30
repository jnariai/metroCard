package com.jnariai.dto;
import com.jnariai.shared.PassagerType;

public record RequestUserDto (String name, String email, String password, PassagerType passagerType){
    public RequestUserDto(String name, String email, String password, PassagerType passagerType){
        this.name = name;
        this.email = email;
        this.password = password;
        this.passagerType = passagerType;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public PassagerType passagerType() {
        return passagerType;
    }
}
