/**
 * VUP_SplashFrame is the main class and Splash screen
 * of the Virtual University of Programming java program.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */

import javax.swing.*;
@SuppressWarnings("ALL")
public class VUP_SplashFrame extends JFrame {
    VUP_SplashFrame(){
        VUP_Sounds clip = new VUP_Sounds();
        clip.setSoundSettings(true);
        clip.setMusicSettings(true);
        clip.startMusic(1);
        int SCREEN_WIDTH = 1060;
        int SCREEN_HEIGHT = 660;

        JLabel logo = new JLabel(new ImageIcon(getClass().getResource("resources/SplashFrame.gif")));
        logo.setVerticalAlignment(JLabel.CENTER);
        logo.setHorizontalAlignment(JLabel.CENTER);

        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Virtual University of Programming");
        this.setIconImage(new ImageIcon(getClass().getResource("resources/Logo.png")).getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(logo);
        // automatically redirects to next frame after splash frame
        try {
            Thread.sleep(4500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new VUP_ClickEmail(clip);
        this.dispose();
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        new VUP_SplashFrame();
    }
}
