package com.jnariai.metrocard.dto;

import org.springframework.stereotype.Component;

import com.jnariai.metrocard.Metrocard;

@Component
public class MetrocardMapper {
  public MetrocardDTO metrocardToMetrocardDTO(Metrocard metrocard){
    if (metrocard == null){
      return null;
    }
    return new MetrocardDTO(metrocard.getId(), metrocard.getBalance(), metrocard.isAutoRecharge(), metrocard.isActive(), metrocard.getUser().getId());
  }
}
