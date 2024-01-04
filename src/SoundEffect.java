import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
  public static void play(String soundFilePath) {
    try {
      File soundFile = new File(soundFilePath);
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }
}