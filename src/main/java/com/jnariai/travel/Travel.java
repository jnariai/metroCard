package com.jnariai.travel;

import com.jnariai.metrocard.Metrocard;
import com.jnariai.shared.PassengerType;
import com.jnariai.shared.Station;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Travel implements Serializable {
  @Id
  @GeneratedValue (strategy = GenerationType.UUID)
  private String id;
  private int cost;
  @Column (name = "passenger_type")
  @Enumerated (EnumType.STRING)
  private PassengerType passengerType;
  private boolean hasDiscount;
  private BigDecimal fee = BigDecimal.valueOf(0);
  @Enumerated (EnumType.STRING)
  private Station station;
  @CreationTimestamp
  private Timestamp boughtAt;
  @ManyToOne
  @JoinColumn (name = "metrocard_id")
  private Metrocard metrocard;
}
