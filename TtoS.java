import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.util.Scanner;
public class voice {
     
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice == null) {
            System.out.println("Cannot find voice: kevin16");
            System.exit(1);
        }
        voice.allocate();
        System.out.print("Enter a text to be converted into speech: ");
        String userInput = sc.nextLine();
        
        voice.speak(userInput);
        voice.deallocate();
    }
}
