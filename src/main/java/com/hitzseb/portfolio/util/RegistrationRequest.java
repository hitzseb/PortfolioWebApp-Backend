package com.hitzseb.portfolio.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class RegistrationRequest {
	private String email;
	private String password;
}