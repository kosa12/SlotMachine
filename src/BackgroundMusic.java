import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BackgroundMusic {
  private static BackgroundMusic instance;
  private Clip clip;
  private int referenceCount;

  private BackgroundMusic() {
    referenceCount = 0;
  }

  public static BackgroundMusic getInstance() {
    if (instance == null) {
      instance = new BackgroundMusic();
    }
    return instance;
  }

  public void addReference() {
    referenceCount++;
    if (referenceCount == 1) {
      play("music/background.wav");
    }
  }

  public void removeReference() {
    referenceCount--;
    if (referenceCount == 0) {
      stop();
    }
  }

  private void play(String musicFilePath) {
    try {
      File soundFile = new File(musicFilePath);
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }

  private void stop() {
    if (clip != null && clip.isRunning()) {
      clip.stop();
      clip.close();
    }
  }
}
