package test.aws.polly;

import java.io.IOException;
import java.io.InputStream;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.*;

import javazoom.jl.decoder.JavaLayerException;

public class CustomPolly {

    private AmazonPolly amazonPolly;

    public CustomPolly(Region region) {
        //
        // Use your access key id and access secret key
        // Obtain it from AWS console
        //

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AKIAQIJRRYRU3WLALJRN",
                "XLTeSOP2lnPIqTUrhyAVWhmac8Fjeiv+nf44Tehm");
        //
        // Create an Amazon Polly client in a specific region
        //
        this.amazonPolly = AmazonPollyClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(DefaultAWSCredentialsProviderChain.getInstance().getCredentials())).withRegion(region.getName()).build();
    }

    public void play(String text) throws IOException, JavaLayerException {
        //
        // Get the audio stream created using the text
        //
        InputStream speechStream = this.synthesize(text, OutputFormat.Mp3);
        //
        // Play the audio
        //
        AudioPlayer.play(speechStream);
    }

    public InputStream synthesize(String text, OutputFormat format) throws IOException {
        //
        // Get the default voice
        //
        Voice voice = this.getVoice();
        //
        // Create speech synthesis request comprising of information such as following:
        // Text
        // Voice
        // The detail will be used to create the speech
        //
        SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest().withText(text).withVoiceId(voice.getId()).withOutputFormat(format).withEngine(Engine.Neural);
        //
        // Create the speech
        //
        SynthesizeSpeechResult synthRes = this.amazonPolly.synthesizeSpeech(synthReq);
        //
        // Returns the audio stream
        //
        return synthRes.getAudioStream();
    }

    public Voice getVoice() {
        //
        // Create describe voices request.
        //
        DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();
        // Synchronously ask Amazon Polly to describe available TTS voices.
        DescribeVoicesResult describeVoicesResult = this.amazonPolly.describeVoices(describeVoicesRequest);
        return describeVoicesResult.getVoices().get(0);
    }


    public void getDescription(){

        System.out.println(this.amazonPolly.describeVoices(new DescribeVoicesRequest()));
    }
}