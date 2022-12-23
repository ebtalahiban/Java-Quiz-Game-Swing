/**
 * Official start of the game play.
 * Displays the current level (year and semester).
 *
 * @author  Erica Talahiban
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
@SuppressWarnings("ALL")
public class VUP_LevelsFrame extends JFrame implements MouseListener {
    VUP_Sounds clip;
    VUP_DoneQuestions doneQuestions;
    String playerName, levelname;
    int currentLevel, totalPoints, mistakes;
    boolean passSelected, addTimeSelected;
    JButton quitButton, nextButton;

    VUP_LevelsFrame(String name, VUP_Sounds clip, int level, int points, int countWrong, boolean pass, boolean addTime, VUP_DoneQuestions doneQuestions){
        this.clip = clip;
        this.doneQuestions = doneQuestions;
        this.playerName = name;
        this.levelname = "level" + String.valueOf(level) + ".png";
        this.currentLevel = level;
        this.mistakes = countWrong;
        this.passSelected = pass;
        this.addTimeSelected = addTime;

        this.totalPoints = points;
        int SCREEN_WIDTH = 1060;
        int SCREEN_HEIGHT = 660;

        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("resources/levels/" + levelname)));
        background.setVerticalAlignment(JLabel.CENTER);
        background.setHorizontalAlignment(JLabel.CENTER);

        quitButton = new JButton();
        quitButton = new JButton();
        quitButton.setBounds(30, 560, 40, 40);
        quitButton.setIcon(new ImageIcon(getClass().getResource("resources/settingsIcon.png")));
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);
        quitButton.addMouseListener(this);

        nextButton = new JButton();
        nextButton = new JButton();
        nextButton.setBounds(800, 520, 223, 75);
        nextButton.setIcon(new ImageIcon(getClass().getResource("resources/nextButton.png")));
        nextButton.setContentAreaFilled(false);
        nextButton.setBorderPainted(false);
        nextButton.addMouseListener(this);

        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Virtual University of Programming");
        this.setIconImage(new ImageIcon(getClass().getResource("resources/Logo.png")).getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(pane);
        this.add(background);
        pane.add(quitButton);
        pane.add(nextButton);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /**
         * Redirects to the next frame
         */
        if(e.getSource()==nextButton){
            clip.startSound(7);
            new VUP_LevelIntro(playerName, clip, currentLevel, totalPoints, mistakes, passSelected, addTimeSelected, doneQuestions);
            this.dispose();
        }
        /**
         * Redirects back to the main menu and end the current gameplay
         * without saving game data
         */
        if(e.getSource()==quitButton){
            clip.startSound(7);
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?\n(Your current progress will not be saved.)", "Notice", 0);
            if (result == JOptionPane.YES_OPTION){
                new VUP_MainFrame(playerName, clip);
                this.dispose();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==quitButton){
            quitButton.setIcon(new ImageIcon(getClass().getResource("resources/settingsIconHover.png")));
            quitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==nextButton){
            nextButton.setIcon(new ImageIcon(getClass().getResource("resources/nextHover.png")));
            nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==quitButton){
            quitButton.setIcon(new ImageIcon(getClass().getResource("resources/settingsIcon.png")));
            quitButton.setCursor(null);
        }
        if(e.getSource()==nextButton){
            nextButton.setIcon(new ImageIcon(getClass().getResource("resources/nextButton.png")));
            nextButton.setCursor(null);
        }
    }
}
