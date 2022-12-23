/**
 * Main Frame or Main menu of the program.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
@SuppressWarnings("ALL")
public class VUP_MainFrame extends JFrame implements MouseListener {
    VUP_DoneQuestions doneQuestions = new VUP_DoneQuestions();
    VUP_Sounds clip, sfx = new VUP_Sounds();
    String playerName;
    int startLevel = 1;
    int initialPoints = 0;
    int initialMistakes = 0;
    boolean passSelected = true;
    boolean addTimeSelected = true;
    private JButton startButton, topButton, howButton, settingsButton, dropButton;

    VUP_MainFrame(String name, VUP_Sounds clip){
        this.playerName = name;
        this.clip = clip;
        int SCREEN_WIDTH = 1060;
        int SCREEN_HEIGHT = 660;

        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("resources/mainFrameBG.png")));
        background.setVerticalAlignment(JLabel.CENTER);
        background.setHorizontalAlignment(JLabel.CENTER);

        startButton = new JButton();
        startButton = new JButton();
        startButton.setBounds(383, 230, 295, 98);
        startButton.setIcon(new ImageIcon(getClass().getResource("resources/startButton.png")));
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.addMouseListener(this);

        topButton = new JButton();
        topButton = new JButton();
        topButton.setBounds(88, 350, 295, 98);
        topButton.setIcon(new ImageIcon(getClass().getResource("resources/topButton.png")));
        topButton.setContentAreaFilled(false);
        topButton.setBorderPainted(false);
        topButton.addMouseListener(this);

        howButton = new JButton();
        howButton = new JButton();
        howButton.setBounds(678, 350, 295, 98);
        howButton.setIcon(new ImageIcon(getClass().getResource("resources/howButton.png")));
        howButton.setContentAreaFilled(false);
        howButton.setBorderPainted(false);
        howButton.addMouseListener(this);

        settingsButton = new JButton();
        settingsButton = new JButton();
        settingsButton.setBounds(88, 470, 295, 98);
        settingsButton.setIcon(new ImageIcon(getClass().getResource("resources/settingsButton.png")));
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
        settingsButton.addMouseListener(this);

        dropButton = new JButton();
        dropButton = new JButton();
        dropButton.setBounds(678, 470, 295, 98);
        dropButton.setIcon(new ImageIcon(getClass().getResource("resources/dropButton.png")));
        dropButton.setContentAreaFilled(false);
        dropButton.setBorderPainted(false);
        dropButton.addMouseListener(this);

        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Virtual University of Programming");
        this.setIconImage(new ImageIcon(getClass().getResource("resources/Logo.png")).getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(pane);
        this.add(background);
        pane.add(startButton);
        pane.add(topButton);
        pane.add(howButton);
        pane.add(settingsButton);
        pane.add(dropButton);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /**
         * Start the game and redirect to levels frame.
         */
        if(e.getSource()==startButton){
            clip.startSound(7);
            new VUP_LevelsFrame(playerName, clip, startLevel, initialPoints, initialMistakes, passSelected, addTimeSelected, doneQuestions);
            this.dispose();
        }
        /**
         * Redirect to the top students frame
         */
        if(e.getSource()==topButton){
            clip.startSound(7);
            new VUP_TopFrame(playerName, clip);
            this.dispose();
        }
        /**
         * Redirect to the How to play frame
         */
        if(e.getSource()==howButton){
            clip.startSound(7);
            new VUP_HowToPlay(playerName, clip);
            this.dispose();
        }
        /**
         * Redirect to the settings frame
         */
        if(e.getSource()==settingsButton){
            clip.startSound(7);
            new VUP_SettingsFrame(playerName, clip);
            this.dispose();
        }
        /**
         * Option to close and exit the game
         */
        if(e.getSource()==dropButton){
            clip.startSound(7);
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to drop out and exit the game?", "Notice", 0);
            if (result == JOptionPane.YES_OPTION){
                clip.stop();
                System.exit(0);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==startButton){
            startButton.setIcon(new ImageIcon(getClass().getResource("resources/startHover.png")));
            startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==topButton){
            topButton.setIcon(new ImageIcon(getClass().getResource("resources/topHover.png")));
            topButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==howButton){
            howButton.setIcon(new ImageIcon(getClass().getResource("resources/howHover.png")));
            howButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==settingsButton){
            settingsButton.setIcon(new ImageIcon(getClass().getResource("resources/settingsHover.png")));
            settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==dropButton){
            dropButton.setIcon(new ImageIcon(getClass().getResource("resources/dropHover.png")));
            dropButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==startButton){
            startButton.setIcon(new ImageIcon(getClass().getResource("resources/startButton.png")));
            startButton.setCursor(null);
        }
        if(e.getSource()==topButton){
            topButton.setIcon(new ImageIcon(getClass().getResource("resources/topButton.png")));
            topButton.setCursor(null);
        }
        if(e.getSource()==howButton){
            howButton.setIcon(new ImageIcon(getClass().getResource("resources/howButton.png")));
            howButton.setCursor(null);
        }
        if(e.getSource()==settingsButton){
            settingsButton.setIcon(new ImageIcon(getClass().getResource("resources/settingsButton.png")));
            settingsButton.setCursor(null);
        }
        if(e.getSource()==dropButton){
            dropButton.setIcon(new ImageIcon(getClass().getResource("resources/dropButton.png")));
            dropButton.setCursor(null);
        }
    }
}
