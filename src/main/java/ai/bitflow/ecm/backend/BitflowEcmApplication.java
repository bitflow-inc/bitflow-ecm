package ai.bitflow.ecm.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

/**
 * 
 * @author metho
 */
@SpringBootApplication
public class BitflowEcmApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BitflowEcmApplication.class);
	    app.addListeners(new ApplicationPidFileWriter());
	    app.run(args);
	}

}
