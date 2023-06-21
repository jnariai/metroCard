package com.jnariai.travel;

import com.jnariai.exceptions.InsufficientFundsException;
import com.jnariai.metrocard.Metrocard;
import com.jnariai.metrocard.MetrocardRepository;
import com.jnariai.shared.PassengerType;
import com.jnariai.shared.Station;
import com.jnariai.travel.dto.CreatedTravelDTO;
import com.jnariai.travel.dto.TravelDTO;
import com.jnariai.travel.pojo.CollectionSummary;
import com.jnariai.travel.pojo.PassengerSummary;
import com.jnariai.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class TravelService {
	private final TravelRepository travelRepository;
	private final MetrocardRepository metrocardRepository;
	private final UserRepository userRepository;
	private final BigDecimal discount = BigDecimal.valueOf(0.5);
	private final BigDecimal fee = BigDecimal.valueOf(0.02);

	public CreatedTravelDTO createTravel(TravelDTO travelDTO) {
		String metrocardId = travelDTO.metrocardId();
		Metrocard metrocard = getMetrocardById(metrocardId);
		int travelsToday = countTravelsToday(metrocardId);
		PassengerType passengerType = getPassengerType(metrocard);
		int price = getPrice(passengerType);
		BigDecimal balance = metrocard.getBalance();
		boolean hasDiscount = travelsToday != 0 && travelsToday % 2 == 0;
		if (hasDiscount) {
			price = discountedPrice(price);
		}
		Travel travel = createNewTravel(price, passengerType, travelDTO.station(), metrocard, hasDiscount);

		if (balance.compareTo(BigDecimal.valueOf(price)) >= 0) {
			return processSuccessfulTravel(travel);
		}

		if (!metrocard.isAutoRecharge()) {
			throw new InsufficientFundsException();
		}

		BigDecimal remainingCost = BigDecimal.valueOf(price).subtract(balance);
		return processInsufficientFunds(travel, remainingCost);
	}

	public List<CollectionSummary> getCollectionSummary() {
		return this.travelRepository.getCollectionSummary();
	}

	public List<PassengerSummary> getPassengerSummary() {
		return this.travelRepository.getPassengerSummary();
	}

	private Metrocard getMetrocardById(String metrocardId) {
		return metrocardRepository.findById(metrocardId).orElseThrow(EntityNotFoundException::new);
	}

	private int countTravelsToday(String metrocardId) {
		return metrocardRepository.countTravelsForMetrocardToday(metrocardId);
	}

	private PassengerType getPassengerType(Metrocard metrocard) {
		return userRepository.findById(metrocard.getUser().getId()).orElseThrow(EntityNotFoundException::new).getPassengerType();
	}

	private int getPrice(PassengerType passengerType) {
		return switch (passengerType) {
			case ADULT -> 200;
			case SENIOR -> 100;
			case KID -> 50;
			default -> throw new IllegalArgumentException("Invalid passenger type");
		};
	}

	private int discountedPrice(int price) {
		return BigDecimal.valueOf(price).multiply(discount).intValue();
	}

	private Travel createNewTravel(int price, PassengerType passengerType, Station station, Metrocard metrocard, boolean hasDiscount) {
		Travel travel = new Travel();
		travel.setCost(price);
		travel.setPassengerType(passengerType);
		travel.setStation(station);
		travel.setMetrocard(metrocard);
		travel.setHasDiscount(hasDiscount);
		return travel;
	}

	private CreatedTravelDTO processSuccessfulTravel(Travel travel) {
		Metrocard metrocard = travel.getMetrocard();
		BigDecimal balance = metrocard.getBalance();
		metrocard.setBalance(balance.subtract(BigDecimal.valueOf(travel.getCost())));
		metrocardRepository.save(metrocard);
		Travel newTravel = travelRepository.save(travel);
		return new CreatedTravelDTO(newTravel.getId(), newTravel.getCost(), newTravel.isHasDiscount(), newTravel.getFee(), newTravel.getStation(), newTravel.getBoughtAt());
	}

	private CreatedTravelDTO processInsufficientFunds(Travel travel, BigDecimal remainingCost) {
		Metrocard metrocard = travel.getMetrocard();
		metrocard.setBalance(BigDecimal.ZERO);
		travel.setFee(remainingCost.multiply(fee));
		metrocardRepository.save(metrocard);
		Travel newTravel = travelRepository.save(travel);
		return new CreatedTravelDTO(newTravel.getId(), newTravel.getCost(), newTravel.isHasDiscount(), newTravel.getFee(), newTravel.getStation(), newTravel.getBoughtAt());
	}
}
