package com.jnariai.travel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;

import com.jnariai.metrocard.Metrocard;
import com.jnariai.shared.PassangerType;
import com.jnariai.shared.Station;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Travel implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private Long cost;
  @Enumerated(EnumType.STRING)
  private PassangerType passangerType;
  private boolean hasDiscount;
  private BigDecimal fee;
  @Enumerated(EnumType.STRING)
  private Station station;
  @CreatedDate
  private Timestamp boughtAt;
  @ManyToOne
  @JoinColumn(name = "metrocard_id")
  private Metrocard metrocard;
}
