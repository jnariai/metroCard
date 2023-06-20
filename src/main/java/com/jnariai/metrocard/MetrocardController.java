package com.jnariai.metrocard;


import com.jnariai.metrocard.dto.CreateMetrocardDTO;
import com.jnariai.metrocard.dto.DepositMoneyMetrocardDTO;
import com.jnariai.metrocard.dto.MetrocardDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/metrocards")
@AllArgsConstructor
public class MetrocardController {
  private final MetrocardService metrocardService;

  @PostMapping()
  public ResponseEntity<MetrocardDTO> createMetrocard(@RequestBody CreateMetrocardDTO createMetrocardDTO){
    return ResponseEntity.created(null).body(metrocardService.createMetrocard(createMetrocardDTO));
  }

  @PutMapping("/balance/{id}")
  public ResponseEntity<Void> depositMoney(@PathVariable("id") String id, @RequestBody @Valid DepositMoneyMetrocardDTO depositMoneyMetrocardDTO) {
    metrocardService.depositMoney(id, depositMoneyMetrocardDTO);
    return ResponseEntity.ok().build();
  }

  
}
