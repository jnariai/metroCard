package com.jnariai.metrocard;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jnariai.metrocard.dto.CreateMetrocardDTO;
import com.jnariai.metrocard.dto.DepositMoneyMetrocardDTO;
import com.jnariai.metrocard.dto.MetrocardDTO;
import com.jnariai.metrocard.dto.MetrocardMapper;
import com.jnariai.user.User;
import com.jnariai.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MetrocardService {
  private final MetrocardRepository metrocardRepository;
  private final UserRepository userRepository;
  private final MetrocardMapper metrocardMapper;


  public ResponseEntity<MetrocardDTO> createMetrocard(CreateMetrocardDTO createMetrocardDTO){
    String userId = createMetrocardDTO.userId(); 
    User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    Metrocard metrocard = new Metrocard();
    metrocard.setUser(user);
    Metrocard newMetrocard = metrocardRepository.save(metrocard);
    MetrocardDTO metrocardDTO = metrocardMapper.metrocardToMetrocardDTO(newMetrocard);
    return ResponseEntity.created(null).body(metrocardDTO);
  }

  public ResponseEntity<Void> depositMoney(String id, DepositMoneyMetrocardDTO depositMoneyMetrocardDTO){
    System.out.println(id);
    Metrocard metrocard = metrocardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    BigDecimal money = depositMoneyMetrocardDTO.money();
    metrocard.setBalance(metrocard.getBalance().add(money));
    metrocardRepository.save(metrocard);
    return ResponseEntity.ok().build();
  }


}
