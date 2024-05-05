package com.example.customvalidation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AuthorisationTimespan {
	public LocalDateTime startTime = LocalDateTime.now();
	public LocalDateTime endTime = LocalDateTime.now();
}
