package com.jnariai.admin;

import com.jnariai.travel.TravelService;
import com.jnariai.travel.pojo.CollectionSummary;
import com.jnariai.travel.pojo.PassengerSummary;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping ("/api/admin")
public class AdminController {
	private final TravelService travelService;

	@GetMapping ("/collection")
	public ResponseEntity<List<CollectionSummary>> getCollectionSummary() {
		return ResponseEntity.ok().body(travelService.getCollectionSummary());
	}

	@GetMapping ("/passenger")
	public ResponseEntity<List<PassengerSummary>> getPassengerSummary() {
		return ResponseEntity.ok().body(travelService.getPassengerSummary());
	}
}
