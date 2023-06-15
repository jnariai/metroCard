package com.jnariai.travel;

import com.jnariai.exceptions.InsufficientFundsException;
import com.jnariai.metrocard.Metrocard;
import com.jnariai.metrocard.MetrocardRepository;
import com.jnariai.shared.PassangerType;
import com.jnariai.shared.Station;
import com.jnariai.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TravelService {
	private final TravelRepository travelRepository;
	private final MetrocardRepository metrocardRepository;
	private final UserRepository userRepository;
	private final BigDecimal discount = BigDecimal.valueOf(0.5);
	private final BigDecimal fee = BigDecimal.valueOf(0.02);

	public ResponseEntity<Object> createTravel(TravelDTO travelDTO) {
		String metrocardId = travelDTO.metrocardId();
		Metrocard metrocard = getMetrocardById(metrocardId);
		int travelsToday = countTravelsToday(metrocardId);
		PassangerType passangerType = getPassangerType(metrocard);
		int price = getPrice(passangerType);
		BigDecimal balance = metrocard.getBalance();
		boolean hasDiscount = travelsToday != 0 && travelsToday % 2 == 0;
		if (hasDiscount) {
			price = discountedPrice(price);
		}
		Travel travel = createNewTravel(price, passangerType, travelDTO.station(), metrocard, hasDiscount);

		if (balance.compareTo(BigDecimal.valueOf(price)) >= 0) {
			processSuccessfulTravel(travel);
			return ResponseEntity.ok().build();
		}

		if (!metrocard.isAutoRecharge()) {
			throw new InsufficientFundsException();
		}

		BigDecimal remainingCost = BigDecimal.valueOf(price).subtract(balance);
		processInsufficientFunds(travel, remainingCost);
		return ResponseEntity.ok().build();
	}

	private Metrocard getMetrocardById(String metrocardId) {
		return metrocardRepository.findById(metrocardId).orElseThrow(EntityNotFoundException::new);
	}

	private int countTravelsToday(String metrocardId) {
		return metrocardRepository.countTravelsForMetrocardToday(metrocardId);
	}

	private PassangerType getPassangerType(Metrocard metrocard) {
		return userRepository.findById(metrocard.getUser().getId()).orElseThrow(EntityNotFoundException::new).getPassangerType();
	}

	private int getPrice(PassangerType passengerType) {
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

	private Travel createNewTravel(int price, PassangerType passangerType, Station station, Metrocard metrocard, boolean hasDiscount) {
		Travel travel = new Travel();
		travel.setCost(price);
		travel.setPassangerType(passangerType);
		travel.setStation(station);
		travel.setMetrocard(metrocard);
		travel.setHasDiscount(hasDiscount);
		return travel;
	}

	private void processSuccessfulTravel(Travel travel) {
		travelRepository.save(travel);
		Metrocard metrocard = travel.getMetrocard();
		BigDecimal balance = metrocard.getBalance();
		metrocard.setBalance(balance.subtract(BigDecimal.valueOf(travel.getCost())));
		metrocardRepository.save(metrocard);
	}

	private void processInsufficientFunds(Travel travel, BigDecimal remainingCost) {
		Metrocard metrocard = travel.getMetrocard();
		metrocard.setBalance(BigDecimal.ZERO);
		travel.setFee(remainingCost.multiply(fee));
		metrocardRepository.save(metrocard);
		travelRepository.save(travel);
	}
}
