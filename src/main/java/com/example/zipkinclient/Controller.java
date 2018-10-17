package com.example.zipkinclient;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class Controller {

	private final String[] keys = new String[] {"A","B","C"};
	private final String[] values = new String[] {"1","2","3"};
	private static Random r = new Random();
	
	@Autowired Tracer tracer;
	
//	@NewSpan(name="randomDelay")
	private void randomDelay() {
		Span newSpan = this.tracer.createSpan("randomDelay");
		int rand = r.nextInt(100);
		try {
			// ...
			// You can tag a span
			this.tracer.addTag("randValue", String.valueOf(rand));
			// ...
			// You can log an event on a span
			newSpan.logEvent("randomDelayValue");
			try {
				Thread.sleep(r.nextInt(100));
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} finally {
			// Once done remember to close the span. This will allow collecting
			// the span to send it to Zipkin
			this.tracer.close(newSpan);
		}
	}
	
	@RequestMapping(value = "/keys", method = RequestMethod.GET)
	public String[] getKeys() {
		return keys ;
	}

	@RequestMapping(value = "/values", method = RequestMethod.GET)
	public String[] getValues() {
		randomDelay();
		return values ;
	}

}
