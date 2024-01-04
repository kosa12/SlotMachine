import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BackgroundMusic {
  private static BackgroundMusic instance;
  private Clip clip;
  private int referenceCount;
  private float volume; // New variable to store the volume level

  private BackgroundMusic() {
    referenceCount = 0;
    volume = 1.0f; // Set default volume to maximum (1.0)
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

  public float getVolume() {
    return volume;
  }

  public void setVolume(float volume) {
    this.volume = volume;
    if (clip != null) {
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      float gain = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
      gainControl.setValue(gain);
    }
  }

  private void play(String musicFilePath) {
    try {
      File soundFile = new File(musicFilePath);
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);

      // Set the volume
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      float gain = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
      gainControl.setValue(gain);

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
