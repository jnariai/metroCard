package com.jnariai.metrocard.dto;

import com.jnariai.metrocard.Metrocard;
import org.springframework.stereotype.Component;

@Component
public class MetrocardMapper {
  public MetrocardDTO metrocardToMetrocardDTO(Metrocard metrocard) {
    if (metrocard == null) {
      return null;
    }
    return new MetrocardDTO(metrocard.getId(),
                            metrocard.getBalance(),
                            metrocard.isAutoRecharge(),
                            metrocard.isActive(),
                            metrocard.getUser().getId());
  }

  public MetrocardUserDTO metrocardToMetrocardUserDTO(Metrocard metrocard) {
    if (metrocard == null) {
      return null;
    }
    return new MetrocardUserDTO(metrocard.getId(),
                                metrocard.getBalance(),
                                metrocard.isAutoRecharge(),
                                metrocard.isActive());
  }
}
