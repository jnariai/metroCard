package com.jnariai.travel;

import com.jnariai.travel.dto.CreatedTravelDTO;
import com.jnariai.travel.dto.TravelDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/travels")
@AllArgsConstructor
@Tag(name = "Travel controller")
public class TravelController {
	private final TravelService travelService;

	@PostMapping
	@Operation (summary = "Create travel")
	@ApiResponses (value = {@ApiResponse (responseCode = "201", description = "Created")})
	public ResponseEntity<CreatedTravelDTO> createTravel(@RequestBody @Valid TravelDTO travelDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.travelService.createTravel(travelDTO));
	}
}