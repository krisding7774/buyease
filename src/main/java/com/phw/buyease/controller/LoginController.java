package com.phw.buyease.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class LoginController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<Object> userRegister(@Validated @RequestBody(required = true) RegisterReqVo reqVo) {
		String msg = authService.registerUserInfo(reqVo);
		return ResponseEntity.ok(msg);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<Object> userLogin(@Validated @RequestBody(required = true) LoginReqVo reqVo) {
		String result = "fuck you";

		result = authService.verifyUser(reqVo);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping(value = "/testToken")
	public ResponseEntity<Object> testToken(@RequestHeader(required = true) String token) {
		Claims test = authService.testToken(token);
		return new ResponseEntity<>(test, HttpStatus.OK);
	}

}
