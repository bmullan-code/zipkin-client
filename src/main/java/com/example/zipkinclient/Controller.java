package com.example.zipkinclient;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class Controller {

	private final String[] keys = new String[] {"A","B","C"};
	private final String[] values = new String[] {"1","2","3"};
	
	@RequestMapping(value = "/keys", method = RequestMethod.GET)
	public String[] getKeys() {
		return keys ;
	}

	@RequestMapping(value = "/values", method = RequestMethod.GET)
	public String[] getValues() {
		return values ;
	}

}
