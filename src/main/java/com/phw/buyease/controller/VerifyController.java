package com.forum.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forum.api.service.customerize.VerifyService;

@RestController
@RequestMapping("forum/verify")
public class VerifyController {

	@Autowired
	private VerifyService verifyService;

	@PostMapping("/email")
	public ResponseEntity<Object> verifyEmail(@RequestParam(name = "email", required = true) String email) {
		String msg = verifyService.isEmailPresent(email);
		return ResponseEntity.ok(msg);
	}

}
