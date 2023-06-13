package com.jnariai.metrocard;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jnariai.metrocard.dto.CreateMetrocardDTO;
import com.jnariai.metrocard.dto.DepositMoneyMetrocardDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@RestController
@Validated
@RequestMapping("/api/metrocards")
@AllArgsConstructor
public class MetrocardController {
  private final MetrocardService metrocardService;

  @PostMapping("/users")
  @ResponseStatus(code = HttpStatus.CREATED)
  public ResponseEntity createMetrocard(@RequestBody CreateMetrocardDTO metrocardDTO){
    metrocardService.createMetrocard(metrocardDTO.userId());
  }

  @PostMapping("/balance/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public void depositMoney(@PathVariable("id") String id, @RequestBody @Positive DepositMoneyMetrocardDTO depositMoneyMetrocardDTO){
    BigDecimal money = depositMoneyMetrocardDTO.money();
    metrocardService.depositMoney(id, money);
  }

  
}
