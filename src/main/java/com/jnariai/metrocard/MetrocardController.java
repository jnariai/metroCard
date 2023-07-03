package com.jnariai.metrocard;


import com.jnariai.metrocard.dto.*;
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

@RestController
@Validated
@RequestMapping ("/api/metrocards")
@AllArgsConstructor
@Tag (name = "Metrocard Controller")
public class MetrocardController {
	private final MetrocardService metrocardService;

	@Operation (summary = "Get metrocard by user")
	@ApiResponses (value = {@ApiResponse (responseCode = "200", description = "OK"), @ApiResponse (responseCode = "404",
					description = "User not found", content = @Content (mediaType = "text/plain", schema = @Schema (type =
					"string"), examples = @ExampleObject (value = "User not found"))),})
	@GetMapping ("/{id}")
	public ResponseEntity<List<MetrocardUserDTO>> getMetrocardsByUserId(@PathVariable @NotNull String id) {
		return ResponseEntity.ok().body(metrocardService.getMetrocardByUserId(id));
	}

	@Operation (summary = "Create metrocard")
	@ApiResponses (value = {@ApiResponse (responseCode = "201", description = "Created"), @ApiResponse (responseCode =
					"404", description = "User not found", content = @Content (mediaType = "text/plain", schema = @Schema (type
					= "string"), examples = @ExampleObject (value = "User not found"))),})
	@PostMapping ()
	public ResponseEntity<MetrocardDTO> createMetrocard(@RequestBody CreateMetrocardDTO createMetrocardDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(metrocardService.createMetrocard(createMetrocardDTO));
	}

	@Operation (summary = "Deposit money")
	@ApiResponses (value = {@ApiResponse (responseCode = "200", description = "Money deposited", content =
	@Content (mediaType = "text/plain", schema = @Schema (type = "string"), examples = @ExampleObject (value = "Money " +
					"deposited"))), @ApiResponse (responseCode = "404", description = "Metrocard not found", content =
	@Content (mediaType = "text/plain", schema = @Schema (type = "string"), examples = @ExampleObject (value =
					"Metrocard not found"))),})
	@PutMapping ("/balance/{id}")
	public ResponseEntity<String> depositMoney(@PathVariable ("id") String id,
	                                           @RequestBody @Valid DepositMoneyMetrocardDTO depositMoneyMetrocardDTO) {
		metrocardService.depositMoney(id, depositMoneyMetrocardDTO);
		return ResponseEntity.ok().body("Money deposited");
	}

	@Operation (summary = "Toggle auto recharge")
	@ApiResponses (value = {@ApiResponse (responseCode = "200", description = "OK"), @ApiResponse (responseCode = "404",
					description = "Metrocard not found", content = @Content (mediaType = "text/plain", schema = @Schema (type =
					"string"), examples = @ExampleObject (value = "Metrocard not found"))),})
	@PutMapping ("/autorecharge/{id}")
	public ResponseEntity<MetrocardUserDTO> toggleAutoRecharge(@PathVariable @NotNull String id,
	                                                           @RequestBody @Valid MetrocardAutoRechargeDTO metrocardAutoRechargeDTO) {
		return ResponseEntity.ok().body(metrocardService.toggleAutoRecharge(id, metrocardAutoRechargeDTO));
	}


}
