package com.jnariai.controller;

import com.jnariai.dto.CreateUserDto;
import com.jnariai.dto.ListUserDto;
import com.jnariai.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<ListUserDto> list() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public ListUserDto findById(@PathVariable @NotNull String id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ListUserDto create(@RequestBody @Valid @NotNull CreateUserDto userDto) {
        return userService.create(userDto);
    }
}
