package com.jnariai.metrocard;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jnariai.metrocard.dto.CreateMetrocardDTO;
import com.jnariai.metrocard.dto.DepositMoneyMetrocardDTO;
import com.jnariai.metrocard.dto.MetrocardDTO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@Validated
@RequestMapping("/api/metrocards")
@AllArgsConstructor
public class MetrocardController {
  private final MetrocardService metrocardService;

  @PostMapping()
  public ResponseEntity<MetrocardDTO> createMetrocard(@RequestBody CreateMetrocardDTO createMetrocardDTO){
    return metrocardService.createMetrocard(createMetrocardDTO);
  }

  @PutMapping("/balance/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<Void> depositMoney(@PathVariable("id") String id, @RequestBody @Valid DepositMoneyMetrocardDTO depositMoneyMetrocardDTO){
    return metrocardService.depositMoney(id, depositMoneyMetrocardDTO);
  }

  
}
