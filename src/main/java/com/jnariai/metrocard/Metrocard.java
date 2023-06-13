package com.jnariai.metrocard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.jnariai.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Metrocard implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private BigDecimal balance = new BigDecimal(0);
  private boolean autoRecharge = true;
  private boolean active = true;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @OneToMany(mappedBy = "metrocard")
  private List<Travel> travels;
  
}
