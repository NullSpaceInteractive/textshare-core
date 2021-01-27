package com.nullspace.textsharecore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.mongo.*;
import org.springframework.boot.web.server.*;

import java.net.*;

@SpringBootApplication
@org.springframework.web.bind.annotation.RestController
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class TextshareCoreApplication {

	public static final String version = "ALPHA-0.1";
	public static final String about = "text.io would have been so much better";
	public static String ip;

	static {
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@LocalServerPort
	public static int port;

	public TextshareCoreApplication(){
	}


	public static void main(String[] args) {
		SpringApplication.run(TextshareCoreApplication.class, args);
		if (args.length > 0){
			MongoHandler.connectToMongo(args[0]);
		}
	}
}
