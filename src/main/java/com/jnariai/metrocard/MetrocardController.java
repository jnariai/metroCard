package com.jnariai.metrocard;


import com.jnariai.metrocard.dto.CreateMetrocardDTO;
import com.jnariai.metrocard.dto.DepositMoneyMetrocardDTO;
import com.jnariai.metrocard.dto.MetrocardDTO;
import com.jnariai.metrocard.dto.MetrocardUserDTO;
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
public class MetrocardController {
	private final MetrocardService metrocardService;

	@GetMapping ("/{id}")
	public ResponseEntity<List<MetrocardUserDTO>> getMetrocardsByUserId(@PathVariable @NotNull String id) {
		return ResponseEntity.ok().body(metrocardService.getMetrocardByUserId(id));
	}

	@PostMapping ()
	public ResponseEntity<MetrocardDTO> createMetrocard(@RequestBody CreateMetrocardDTO createMetrocardDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(metrocardService.createMetrocard(createMetrocardDTO));
	}

	@PutMapping ("/balance/{id}")
	public ResponseEntity<Void> depositMoney(@PathVariable ("id") String id, @RequestBody @Valid DepositMoneyMetrocardDTO depositMoneyMetrocardDTO) {
		metrocardService.depositMoney(id, depositMoneyMetrocardDTO);
		return ResponseEntity.ok().build();
	}


}
