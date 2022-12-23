/**
 * Allows the player to turn on/off the
 * sounds and music in the program.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
@SuppressWarnings("ALL")
public class VUP_SettingsFrame extends JFrame implements MouseListener {
    VUP_Sounds clip;
    String playerName;
    boolean soundOn;
    JButton backButton;
    JToggleButton soundButton, musicButton;

    VUP_SettingsFrame(String name, VUP_Sounds clip){
        this.playerName = name;
        this.clip = clip;
        int SCREEN_WIDTH = 1060;
        int SCREEN_HEIGHT = 660;

        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("resources/settingsBG.png")));
        background.setVerticalAlignment(JLabel.CENTER);
        background.setHorizontalAlignment(JLabel.CENTER);

        soundButton = new JToggleButton();
        soundButton.setBounds(383, 200, 295, 98);
        soundButton.setIcon(new ImageIcon(getClass().getResource("resources/soundOnBtn.png")));
        soundButton.setSelectedIcon(new ImageIcon(getClass().getResource("resources/soundOffBtn.png")));
        soundButton.setContentAreaFilled(false);
        soundButton.setBorderPainted(false);
        soundButton.addMouseListener(this);
        soundButton.addActionListener(soundButtonAL());

        musicButton = new JToggleButton();
        musicButton.setBounds(383, 330, 295, 98);
        musicButton.setIcon(new ImageIcon(getClass().getResource("resources/musicOnBtn.png")));
        musicButton.setSelectedIcon(new ImageIcon(getClass().getResource("resources/musicOffBtn.png")));
        musicButton.setContentAreaFilled(false);
        musicButton.setBorderPainted(false);
        musicButton.addMouseListener(this);
        musicButton.addActionListener(musicButtonAL());

        backButton = new JButton();
        backButton = new JButton();
        backButton.setBounds(780, 520, 225, 70);
        backButton.setIcon(new ImageIcon(getClass().getResource("resources/backButton.png")));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.addMouseListener(this);

        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Virtual University of Programming");
        this.setIconImage(new ImageIcon(getClass().getResource("resources/Logo.png")).getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(pane);
        this.add(background);
        pane.add(backButton);
        pane.add(soundButton);
        pane.add(musicButton);
        setButtons();
    }
    /**
     * Set the buttons to selected if
     * sound/music is turned off.
     */
    public void setButtons(){
        if(!clip.getSoundSettings()){
            soundButton.setSelected(true);
        }
        if(!clip.getMusicSettings()){
            musicButton.setSelected(true);
        }
    }
    /**
     * Handles the on/off action of the soundButton
     */
    public ActionListener soundButtonAL(){
        ActionListener actionListener = new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                if(soundButton.isSelected()){
                    clip.setSoundSettings(false);
                    clip.stopSFX();
                }
                else{
                    clip.setSoundSettings(true);
                    clip.startSound(7);
                }
            }
        };
        return actionListener;
    }
    /**
     * Handles the on/off action of the musicButton
     */
    public ActionListener musicButtonAL(){
        ActionListener actionListener = new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                if(musicButton.isSelected()){
                    clip.startSound(7);
                    clip.setMusicSettings(false);
                    clip.stop();
                }
                else{
                    clip.startSound(7);
                    clip.startMusic(1);
                    clip.setMusicSettings(true);
                    clip.startMusic(1);
                }
            }
        };
        return actionListener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==backButton){
            clip.startSound(7);
            new VUP_MainFrame(playerName, clip);
            this.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {  }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==soundButton){
            soundButton.setIcon(new ImageIcon(getClass().getResource("resources/soundOnHover.png")));
            soundButton.setSelectedIcon(new ImageIcon(getClass().getResource("resources/soundOffHover.png")));
            soundButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==musicButton){
            musicButton.setIcon(new ImageIcon(getClass().getResource("resources/musicOnHover.png")));
            musicButton.setSelectedIcon(new ImageIcon(getClass().getResource("resources/musicOffHover.png")));
            musicButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==backButton){
            backButton.setIcon(new ImageIcon(getClass().getResource("resources/backHover.png")));
            backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==soundButton){
            soundButton.setIcon(new ImageIcon(getClass().getResource("resources/soundOnBtn.png")));
            soundButton.setSelectedIcon(new ImageIcon(getClass().getResource("resources/soundOffBtn.png")));
            soundButton.setCursor(null);
        }
        if(e.getSource()==musicButton){
            musicButton.setIcon(new ImageIcon(getClass().getResource("resources/musicOnBtn.png")));
            musicButton.setSelectedIcon(new ImageIcon(getClass().getResource("resources/musicOffBtn.png")));
            musicButton.setCursor(null);
        }
        if(e.getSource()==backButton){
            backButton.setIcon(new ImageIcon(getClass().getResource("resources/backButton.png")));
            backButton.setCursor(null);
        }
    }
}
