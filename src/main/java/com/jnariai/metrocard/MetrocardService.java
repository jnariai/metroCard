package com.jnariai.metrocard;

import com.jnariai.metrocard.dto.CreateMetrocardDTO;
import com.jnariai.metrocard.dto.DepositMoneyMetrocardDTO;
import com.jnariai.metrocard.dto.MetrocardDTO;
import com.jnariai.metrocard.dto.MetrocardMapper;
import com.jnariai.user.User;
import com.jnariai.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class MetrocardService {
  private final MetrocardRepository metrocardRepository;
  private final UserRepository userRepository;
  private final MetrocardMapper metrocardMapper;


  public MetrocardDTO createMetrocard(CreateMetrocardDTO createMetrocardDTO) {
    String userId = createMetrocardDTO.userId();
    User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    Metrocard metrocard = new Metrocard();
    metrocard.setUser(user);
    Metrocard newMetrocard = metrocardRepository.save(metrocard);
    return metrocardMapper.metrocardToMetrocardDTO(newMetrocard);
  }

  public void depositMoney(String id, DepositMoneyMetrocardDTO depositMoneyMetrocardDTO) {
    Metrocard metrocard = metrocardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    BigDecimal money = depositMoneyMetrocardDTO.money();
    metrocard.setBalance(metrocard.getBalance().add(money));
    metrocardRepository.save(metrocard);
  }
  
}
