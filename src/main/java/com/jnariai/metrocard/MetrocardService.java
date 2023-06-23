package com.jnariai.metrocard;

import com.jnariai.metrocard.dto.*;
import com.jnariai.user.User;
import com.jnariai.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class MetrocardService {
	private final MetrocardRepository metrocardRepository;
	private final UserRepository userRepository;
	private final MetrocardMapper metrocardMapper;

	public List<MetrocardUserDTO> getMetrocardByUserId(String userId) {
		return metrocardRepository.findByUserId(userId).stream().map(metrocardMapper::metrocardToMetrocardUserDTO).toList();
	}

	public MetrocardDTO createMetrocard(CreateMetrocardDTO createMetrocardDTO) {
		String userId = createMetrocardDTO.userId();
		User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
		Metrocard metrocard = new Metrocard();
		metrocard.setUser(user);
		Metrocard newMetrocard = metrocardRepository.save(metrocard);
		return metrocardMapper.metrocardToMetrocardDTO(newMetrocard);
	}

	public void depositMoney(String id, DepositMoneyMetrocardDTO depositMoneyMetrocardDTO) {
		Metrocard metrocard = metrocardRepository.findById(id)
		                                         .orElseThrow(() -> new EntityNotFoundException("Metrocard not found"));
		BigDecimal money = depositMoneyMetrocardDTO.money();
		metrocard.setBalance(metrocard.getBalance().add(money));
		metrocardRepository.save(metrocard);
	}

	public MetrocardUserDTO toggleAutoRecharge(String id, MetrocardAutoRechargeDTO metrocardAutoRechargeDTO) {
		Metrocard metrocard = metrocardRepository.findById(id)
		                                         .orElseThrow(() -> new EntityNotFoundException("Metrocard not found"));
		metrocard.setAutoRecharge(metrocardAutoRechargeDTO.autoRecharge());
		Metrocard newMetrocard = metrocardRepository.save(metrocard);
		return metrocardMapper.metrocardToMetrocardUserDTO(newMetrocard);
	}

}
