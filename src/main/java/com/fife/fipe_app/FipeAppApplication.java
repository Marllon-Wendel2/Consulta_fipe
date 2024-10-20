package com.fife.fipe_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fife.fipe_app.main.Main;

@SpringBootApplication
public class FipeAppApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FipeAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main =  new Main();
		main.consult();
	}

}
