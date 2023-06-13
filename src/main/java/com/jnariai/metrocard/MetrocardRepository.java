package com.jnariai.metrocard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MetrocardRepository extends JpaRepository<Metrocard, String>{
  // method to get balance
  // then increment
  // update
}
