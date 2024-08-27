import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class TextToSpeechDemo {
    public static void main(String[] args) throws Exception {
        String text = "Hello, welcome to the Text to Speech demo!";
        synthesizeText(text);
    }

    public static void synthesizeText(String text) throws Exception { 
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {    
            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("en-US") // Language code
                    .setSsmlGender(SsmlVoiceGender.NEUTRAL) // Voice gender
                    .build();
            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3) // Output format
                    .build();
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
            ByteString audioContents = response.getAudioContent();
            playAudio(audioContents);
        }
    }

    public static void playAudio(ByteString audioContents) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
      
        byte[] audioData = audioContents.toByteArray();
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(audioData));
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        while (clip.isRunning()) {
            Thread.sleep(100);
        }
        clip.close();
    }
}
