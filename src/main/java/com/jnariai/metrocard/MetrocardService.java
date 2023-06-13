package com.jnariai.metrocard;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.jnariai.exceptions.RecordNotFoundException;
import com.jnariai.user.User;
import com.jnariai.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MetrocardService {
  private final MetrocardRepository metrocardRepository;
  private final UserRepository userRepository;


  public void createMetrocard(String id){
    User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    Metrocard metrocard = new Metrocard();
    metrocard.setUser(user);
    metrocardRepository.save(metrocard);
  }

  public void depositMoney(String id, BigDecimal money){
    userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
  }


}
