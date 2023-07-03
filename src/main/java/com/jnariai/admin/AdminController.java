package com.jnariai.admin;

import com.jnariai.travel.TravelService;
import com.jnariai.travel.pojo.CollectionSummary;
import com.jnariai.travel.pojo.PassengerSummary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping ("/api/admin")
@Tag(name = "Admin controller")
public class AdminController {
	private final TravelService travelService;

	@GetMapping ("/collection")
	@Operation (summary = "Get collection summary")
	@ApiResponses (value = {
					@ApiResponse (responseCode = "200", description = "OK")})
	public ResponseEntity<List<CollectionSummary>> getCollectionSummary() {
		return ResponseEntity.ok().body(travelService.getCollectionSummary());
	}

	@GetMapping ("/passenger")
	@Operation (summary = "Get passanger summary")
	@ApiResponses (value = {
					@ApiResponse (responseCode = "200", description = "OK")})
	public ResponseEntity<List<PassengerSummary>> getPassengerSummary() {
		return ResponseEntity.ok().body(travelService.getPassengerSummary());
	}
}
