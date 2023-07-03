package com.jnariai.user;

import com.jnariai.travel.dto.TravelUserDTO;
import com.jnariai.user.dto.CreateUserDto;
import com.jnariai.user.dto.UserDTO;
import com.jnariai.user.dto.UserPasswordDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping ("/api/users")
@AllArgsConstructor
@Tag (name = "User Controller")
public class UserController {
	private final UserService userService;

	@GetMapping
	@Operation (summary = "Get all users")
	@ApiResponses (value = {@ApiResponse (responseCode = "200", description = "OK")})
	public ResponseEntity<List<UserDTO>> findAll() {
		return ResponseEntity.ok().body(userService.findAll());
	}

	@GetMapping ("/{id}")
	@Operation (summary = "Get user by id")
	@ApiResponses (value = {
					@ApiResponse (responseCode = "200", description = "OK"),
					@ApiResponse (responseCode = "404",
					description = "User not found", content = @Content (mediaType = "text/plain", schema = @Schema (type =
					"string"), examples = @ExampleObject (value = "User not found"))),
					})
	public ResponseEntity<UserDTO> findById(@PathVariable @NotNull String id) {
		return ResponseEntity.ok().body(userService.findById(id));
	}

	@Operation (summary = "Get all travels from user")
	@ApiResponses (value = {
					@ApiResponse (responseCode = "200", description = "OK"),
					@ApiResponse (responseCode = "404",
									description = "User not found", content = @Content (mediaType = "text/plain", schema = @Schema (type =
									"string"), examples = @ExampleObject (value = "User not found"))),
	})
	@GetMapping ("/travel/{id}")
	public ResponseEntity<List<TravelUserDTO>> getTravelByUser(@PathVariable @NotNull String id) {
		return ResponseEntity.ok().body(userService.getTravelByUser(id));
	}

	@Operation (summary = "Create user")
	@ApiResponses (value = {
					@ApiResponse (responseCode = "201", description = "User created"),
					@ApiResponse (responseCode = "409",
									description = "Email already registered", content = @Content (mediaType = "text/plain", schema =
					@Schema (type =
									"string"), examples = @ExampleObject (value = "Email already registered"))),
	})
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody @Valid CreateUserDto userDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
	}

	@Operation (summary = "Update password of user")
	@ApiResponses (value = {
					@ApiResponse (responseCode = "200", description = "Password updated", content = @Content (mediaType = "text" +
									"/plain", schema = @Schema (type = "string"), examples = @ExampleObject (value = "Password updated succesfully"))),
					@ApiResponse (responseCode = "404", description = "User not found", content = @Content (mediaType = "text" +
									"/plain", schema = @Schema (type = "string"), examples = @ExampleObject (value = "User not found"))),
					@ApiResponse (responseCode = "400", description = "Invalid password", content = @Content (mediaType =
					"text/plain", schema = @Schema (type = "string"), examples = @ExampleObject (value = "Invalid password")))})
	@PutMapping ("/{id}")
	public ResponseEntity<String> uppdateUserPassword(@PathVariable @NotNull String id,
	                                                  @RequestBody @Valid UserPasswordDTO userPasswordDTO) {
		try {
			userService.updateUserPassword(id, userPasswordDTO);
			return ResponseEntity.ok("Password updated succesfully");
		} catch (IllegalArgumentException exception) {
			return ResponseEntity.badRequest().body(exception.getMessage());
		}
	}
}
