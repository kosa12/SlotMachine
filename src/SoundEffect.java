import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundEffect {
  public static void play(InputStream soundInputStream) {
    try {
      BufferedInputStream bufferedInputStream = new BufferedInputStream(soundInputStream);
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);

      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);

      clip.addLineListener(event -> {
        if (event.getType() == LineEvent.Type.STOP) {
          clip.close();
        }
      });

      clip.start();
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }
}
