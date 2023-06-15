package com.jnariai.travel;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/travels")
@AllArgsConstructor
public class TravelController {
	private final TravelService travelService;

	@PostMapping
	public ResponseEntity<Object> createTravel(@RequestBody @Valid TravelDTO travelDTO) {
		return this.travelService.createTravel(travelDTO);
	}
}