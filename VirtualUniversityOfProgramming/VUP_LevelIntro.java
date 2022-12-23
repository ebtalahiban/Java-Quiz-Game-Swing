/**
 * GIF UI that displays the current year level and semester.
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
public class VUP_LevelIntro extends JFrame implements MouseListener {
    String playerName, levelname;
    int currentLevel;
    int category_id = 0;
    int totalPoints;
    int mistakes;
    int seconds = 4;
    boolean passSelected, addTimeSelected;
    JLayeredPane pane;
    JLabel background;
    JButton settingsButton;
    VUP_Sounds clip;
    VUP_DoneQuestions doneQuestions;
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            if(seconds<=0) { newQuestion(); }
        }
    });

    VUP_LevelIntro(String name, VUP_Sounds clip, int level, int points, int countWrong, boolean pass, boolean addTime, VUP_DoneQuestions doneQuestions){
        this.clip = clip;
        this.doneQuestions = doneQuestions;
        this.playerName = name;
        this.levelname = "level" + String.valueOf(level) + ".gif";
        this.currentLevel = level;
        this.totalPoints = points;
        this.mistakes = countWrong;
        this.passSelected = pass;
        this.addTimeSelected = addTime;
        int SCREEN_WIDTH = 1060;
        int SCREEN_HEIGHT = 660;

        pane = new JLayeredPane();
        pane.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        background = new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("resources/levelIntro/"+levelname)));
        background.setVerticalAlignment(JLabel.CENTER);
        background.setHorizontalAlignment(JLabel.CENTER);
        background.addMouseListener(this);

        settingsButton = new JButton();
        settingsButton = new JButton();
        settingsButton.setBounds(30, 560, 40, 40);
        settingsButton.setIcon(new ImageIcon(getClass().getResource("resources/settingsIcon.png")));
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
        settingsButton.addMouseListener(this);

        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Virtual University of Programming");
        this.setIconImage(new ImageIcon(getClass().getResource("resources/Logo.png")).getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(pane);
        this.add(background);
        timer.start();
    }
    /**
     * Ends the current frame and
     * opens a new question frame
     */
    public void newQuestion(){
        timer.stop();
        clip.stop();
        clip.startMusic(2);
        new VUP_QuestionFrame(playerName, clip, currentLevel, category_id, totalPoints, mistakes, passSelected, addTimeSelected, doneQuestions);
        this.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        newQuestion();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==settingsButton){
            settingsButton.setIcon(new ImageIcon(getClass().getResource("resources/settingsIconHover.png")));
            settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==settingsButton){
            settingsButton.setIcon(new ImageIcon(getClass().getResource("resources/settingsIcon.png")));
            settingsButton.setCursor(null);
        }
    }
}
