package com.whattodo;

import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whattodo2.service.TestService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@SpringBootApplication(scanBasePackages = {
    "com.whattodo",
    "com.whattodo2"
})
public class Swagger3Application {

	@Autowired
	private TestService testService;
	
	@Value("${sample.value}")
	private String value;
	
	@GetMapping("/get1234")
	public String get1234() {
		log.info("테스트~~~zzz");
		testService.testMethod();
		System.out.println("sample.value = " + this.value);
		return "Swagger 테스트용 GET 메서드";
	}
	
	
	@Operation(summary = "[user-sign-up-1] Sign Up (회원가입)", description = "Sign Up API, \n termsAgree 의 시간은 자동생성된다zzzzzㅋㅋㅋㅋㅋ.")
	@PostMapping("/post1234")
	public ResponseEntity post1234() {
		return null;
	}
	
	public static void main(String[] args) {
		
		System.out.println("# Application main 함수 시작 #");
		
		String profiles = System.getProperty("spring.profiles.default");
		
		if(StringUtils.isEmpty(profiles)) {
			System.setProperty("spring.profiles.default", "test");
		}
		
		System.out.println("> spring.profiles.default ::: " + System.getProperty("spring.profiles.default"));
		
		
		Properties p = System.getProperties();
	    Enumeration keys = p.keys();

	    System.out.println("--------------------");
	    while (keys.hasMoreElements()) {
	      String key = (String) keys.nextElement();
	      String value = (String) p.get(key);
	      System.out.println("# Property : [" + key + " = " + value + "]");
	    }
	    System.out.println("--------------------");
		
		SpringApplication.run(Swagger3Application.class, args);
	}

}
