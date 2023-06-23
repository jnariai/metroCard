package com.jnariai.metrocard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetrocardRepository extends JpaRepository<Metrocard, String> {
	@Query ("SELECT COUNT(travel) FROM Metrocard metrocard JOIN metrocard.travels travel WHERE DATE_TRUNC('day',travel.boughtAt) = CURRENT_DATE AND metrocard.id = :metrocardId")
	int countTravelsForMetrocardToday(@Param ("metrocardId") String metrocardId);

	List<Metrocard> findByUserId(String userId);
}
