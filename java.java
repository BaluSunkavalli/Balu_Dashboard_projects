import java.util.Scanner;
import javax.sound.sampled.*;
import com.sun.speech.freetts.*;

public class TextToSpeechConverter {
    public static void main(String[] args) {
        System.out.println("Welcome to Text-to-Speech Converter");
        System.out.println("------------------------------------");

        Scanner scanner = new Scanner(System.in);
        FreeTTSVoice voice = new FreeTTSVoice();
        
        while (true) {
            System.out.print("Enter the text you want to convert (type 'exit' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program...");
                break;
            }

            voice.speak(input);
        }

        scanner.close();
    }
}

class FreeTTSVoice {
    private final String voiceName = "kevin16"; // Voice name - you can change it based on available voices

    public void speak(String text) {
        System.out.println("Speaking: " + text);
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            VoiceManager voiceManager = VoiceManager.getInstance();
            Voice voice = voiceManager.getVoice(voiceName);

            voice.allocate();
            voice.speak(text);
            voice.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
