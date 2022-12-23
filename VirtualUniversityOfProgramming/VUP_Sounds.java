/**
 * Handles the background music and sound effects
 * in the program.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
public class VUP_Sounds {
    public Clip clip, sfx;
    URL musicURL, sfxURL;
    boolean musicOn, soundOn;
    String mainBGM, quizTimer, correct, wrong, click, win, gameOver;
    /**
     * Initialize paths of the wav files.
     */
    VUP_Sounds(){
        mainBGM = "/resources/sounds/mainBGM.wav";
        quizTimer = "/resources/sounds/quizTimer.wav";
        correct= "/resources/sounds/correct.wav";
        wrong = "/resources/sounds/wrong.wav";
        click = "/resources/sounds/click.wav";
        win = "/resources/sounds/win.wav";
        gameOver = "/resources/sounds/gameOver.wav";
    }

    public void musicChoice(int num){
        try{
            switch (num) {
                case 1 -> musicURL = getClass().getResource(mainBGM);
                case 2 -> musicURL = getClass().getResource(quizTimer);
                case 3 -> musicURL = getClass().getResource(win);
                case 4 -> musicURL = getClass().getResource(gameOver);
            }
            assert musicURL != null;
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicURL);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException ignored) { }
    }
    public void startMusic(int id){
        if(getMusicSettings()){
            musicChoice(id);
        }
    }
    public void stop(){
        clip.stop();
    }
    public void setMusicSettings(boolean musicOn){
        this.musicOn = musicOn;
    }
    public boolean getMusicSettings(){
        return musicOn;
    }

    public void soundChoice(int num){
        try{
            switch (num) {
                case 5 -> sfxURL = getClass().getResource(correct);
                case 6 -> sfxURL = getClass().getResource(wrong);
                case 7 -> sfxURL = getClass().getResource(click);
            }
            assert sfxURL != null;
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(sfxURL);
            sfx = AudioSystem.getClip();
            sfx.open(audioStream);
            sfx.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException ignored) { }
    }
    public void startSound(int id){
        if(getSoundSettings()){
            soundChoice(id);
        }
    }
    public void stopSFX(){
        sfx.stop();
    }
    public void setSoundSettings(boolean soundOn){
        this.soundOn = soundOn;
    }
    public boolean getSoundSettings(){
        return soundOn;
    }
}
