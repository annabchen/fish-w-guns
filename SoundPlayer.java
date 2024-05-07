import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class SoundPlayer implements Runnable {
    private String filePath;
    private boolean loop;
    /*
     * Music for the game run in the independent thread through AudioInputStream
     */
    public SoundPlayer(String filePath, boolean loop) {
        this.filePath = filePath;
        this.loop = loop;
    }

    @Override
    public void run() {
        this.playSound(filePath, loop);
    }

    private void playSound(String filePath, boolean loop) {
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(filePath))) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
                clip.drain(); // Wait for the sound to finish playing if not looping
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        
    }
}
