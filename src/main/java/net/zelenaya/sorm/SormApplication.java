package net.zelenaya.sorm;

import net.zelenaya.sorm.util.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
//@EnableJpaRepositories
public class SormApplication {

	public static void main(String[] args) {
		Util.createProperties();
		SpringApplication.run(SormApplication.class, args);
	}

}
