package com.hitzseb.portfolio.service;

import com.hitzseb.portfolio.model.Person;
import com.hitzseb.portfolio.repo.PersonRepo;
import com.hitzseb.portfolio.model.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.hitzseb.portfolio.model.User;
import com.hitzseb.portfolio.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
	private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
	private final UserRepo userRepo;
	private final BCryptPasswordEncoder bcryptPasswordncoder;
	private final ConfirmationTokenService confirmationTokenService;
	private final PersonRepo personRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}

	public String signUpUser(User user) {
		boolean userExists = userRepo.findByEmail(user.getEmail()).isPresent();

		if (userExists ) {
			
			User registeredUser = (User)userRepo.findByEmail(user.getEmail()).orElse(null);
			
			if (registeredUser.isEnabled() == false) {
				
				String encodedPassword = bcryptPasswordncoder.encode(user.getPassword());
				registeredUser.setPassword(encodedPassword);
				userRepo.save(registeredUser);
				
				String token = UUID.randomUUID().toString();
				ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
						LocalDateTime.now().plusMinutes(15), registeredUser);
				confirmationTokenService.saveConfirmationToken(confirmationToken);
				return token;
			}
			
			else {
				throw new IllegalStateException("email already taken");
			}
			
		}
		
		String encodedPassword = bcryptPasswordncoder.encode(user.getPassword());

		user.setPassword(encodedPassword);
		userRepo.save(user);

		Person person = new Person();
		person.setId(user.getId());
		String userEmail = user.getEmail();
		String personName = userEmail.substring(0, userEmail.indexOf("@"));
		person.setContact(userEmail);
                person.setFullName(personName);
		personRepo.save(person);

		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15), user);
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		return token;
		
	}

	public int enableUser(String email) {
		return userRepo.enableUser(email);
	}

	public Optional<User> userByEmail(String email) {
		return userRepo.findByEmail(email);
	}

}
