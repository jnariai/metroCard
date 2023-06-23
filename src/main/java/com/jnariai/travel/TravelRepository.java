package com.jnariai.travel;

import com.jnariai.travel.pojo.CollectionSummary;
import com.jnariai.travel.pojo.PassengerSummary;
import com.jnariai.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, String> {
	@Query ("SELECT new com.jnariai.travel.pojo.CollectionSummary(t.station, SUM(t.cost), SUM(t.fee), COUNT(CASE WHEN t.hasDiscount = true THEN 1 ELSE NULL END)) " + "FROM Travel t " + "GROUP BY t.station")
	List<CollectionSummary> getCollectionSummary();

	@Query ("SELECT new com.jnariai.travel.pojo.PassengerSummary(t.passengerType, COUNT(*) AS travels)" + "FROM Travel t " + "GROUP BY t.passengerType " + "ORDER BY travels DESC, t.passengerType ASC")
	List<PassengerSummary> getPassengerSummary();

	List<Travel> findAllByMetrocard_User(User user);

}