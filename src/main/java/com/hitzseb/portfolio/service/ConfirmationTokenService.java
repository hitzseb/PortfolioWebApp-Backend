package com.hitzseb.portfolio.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.hitzseb.portfolio.model.ConfirmationToken;
import com.hitzseb.portfolio.repo.ConfirmationTokenRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
	private final ConfirmationTokenRepo confirmationTokenRepo;

	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenRepo.save(token);
	}

	public Optional<ConfirmationToken> getToken(String token) {
		return confirmationTokenRepo.findByToken(token);
	}

	public int setConfirmedAt(String token) {
		return confirmationTokenRepo.updateConfirmedAt(token, LocalDateTime.now());
	}
}
