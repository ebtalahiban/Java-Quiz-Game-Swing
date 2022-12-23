/**
 * Displays lose frame after losing the game.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
@SuppressWarnings("ALL")
public class VUP_LoseFrame extends JFrame implements MouseListener {
    VUP_Sounds clip;
    VUP_DoneQuestions doneQuestions;
    String playerName;
    JButton tryButton, quitButton;
    VUP_LoseFrame(String name, VUP_Sounds clip, VUP_DoneQuestions doneQuestions){
        this.playerName = name;
        this.clip = clip;
        this.doneQuestions = doneQuestions;
        int SCREEN_WIDTH = 1060;
        int SCREEN_HEIGHT = 660;

        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("resources/loseFrameBG.png")));
        background.setVerticalAlignment(JLabel.CENTER);
        background.setHorizontalAlignment(JLabel.CENTER);

        tryButton = new JButton();
        tryButton = new JButton();
        tryButton.setBounds(200, 500, 223, 75);
        tryButton.setIcon(new ImageIcon(getClass().getResource("resources/tryButton.png")));
        tryButton.setContentAreaFilled(false);
        tryButton.setBorderPainted(false);
        tryButton.addMouseListener(this);

        quitButton = new JButton();
        quitButton = new JButton();
        quitButton.setBounds(450, 500, 223, 75);
        quitButton.setIcon(new ImageIcon(getClass().getResource("resources/quitButton.png")));
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);
        quitButton.addMouseListener(this);

        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Virtual University of Programming");
        this.setIconImage(new ImageIcon(getClass().getResource("resources/Logo.png")).getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(pane);
        this.add(background);
        pane.add(tryButton);
        pane.add(quitButton);
        clip.startMusic(4);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /**
         * Restarts the game back to the first level.
         */
        if(e.getSource()==tryButton){
            clip.startSound(7);
            clip.stop();
            clip.startMusic(1);
            new VUP_LevelsFrame(playerName, clip, 1, 0, 0, true, true, doneQuestions);
            this.dispose();
        }
        /**
         * Redirects the game back to the main menu.
         */
        if(e.getSource()==quitButton){
            clip.startSound(7);
            clip.stop();
            clip.startMusic(1);
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
        if(e.getSource()==tryButton){
            tryButton.setIcon(new ImageIcon(getClass().getResource("resources/tryHover.png")));
            tryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==quitButton){
            quitButton.setIcon(new ImageIcon(getClass().getResource("resources/quitHover.png")));
            quitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==tryButton){
            tryButton.setIcon(new ImageIcon(getClass().getResource("resources/tryButton.png")));
            tryButton.setCursor(null);
        }
        if(e.getSource()==quitButton){
            quitButton.setIcon(new ImageIcon(getClass().getResource("resources/quitButton.png")));
            quitButton.setCursor(null);
        }
    }
}
