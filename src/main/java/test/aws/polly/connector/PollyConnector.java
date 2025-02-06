package test.aws.polly.connector;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("polly")
public class PollyConnector {
    @PostMapping
    public String tts(){
        return "s";
    }
}
