package test.aws.polly;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import javazoom.jl.decoder.JavaLayerException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PollyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PollyApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... arg0) throws IOException, JavaLayerException {

		//
		// Sample Hello World Text
		//
//		String sampleText = "Hello World! How are you doing? This is Polly. I am happy to talk with you.";
		//
		// create the CustomPolly class
		//
		CustomPolly customPolly = new CustomPolly(Region.getRegion(Regions.EU_WEST_2));
		//
		// Have CustomPolly play the text to speech
		//
		customPolly.getDescription();
//		customPolly.play(sampleText);
	}
}
