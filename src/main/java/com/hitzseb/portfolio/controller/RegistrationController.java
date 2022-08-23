package com.hitzseb.portfolio.controller;

import com.hitzseb.portfolio.util.RegistrationRequest;
import com.hitzseb.portfolio.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "registration")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class RegistrationController {
	private final RegistrationService registrationService;

	@PostMapping
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}

	@GetMapping(path = "confirm")
	public String confirm(@RequestParam("token") String token) {
		return registrationService.confirmToken(token);
	}

}
