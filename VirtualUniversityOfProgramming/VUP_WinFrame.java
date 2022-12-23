/**
 * Displays name and final grade of player after
 * passing all the questions.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
@SuppressWarnings("ALL")
public class VUP_WinFrame extends JFrame implements MouseListener {
    VUP_Sounds clip;
    String playerName;
    String average;
    JButton nextButton;

    VUP_WinFrame(String name, int points, VUP_Sounds clip){
        this.playerName = name;
        this.average = computePoints(points);
        this.clip = clip;
        int SCREEN_WIDTH = 1060;
        int SCREEN_HEIGHT = 660;

        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("resources/winFrameBG.png")));
        background.setVerticalAlignment(JLabel.CENTER);
        background.setHorizontalAlignment(JLabel.CENTER);

        JLabel nameLabel = new JLabel(playerName, SwingConstants.CENTER);
        nameLabel.setBounds(350, 170, 400, 70);
        nameLabel.setFont(new Font("Arial Rounded MT Bold", 0, 60));

        JLabel gradeLabel = new JLabel("AVERAGE GRADE: " + this.average, SwingConstants.CENTER);
        gradeLabel.setBounds(350, 250, 400, 40);
        gradeLabel.setFont(new Font("Arial Rounded MT Bold", 0, 30));

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
        pane.add(nextButton);
        pane.add(nameLabel);
        pane.add(gradeLabel);
        clip.startMusic(3);
        new VUP_TopStudents(playerName, Float.parseFloat(average));
    }
    /**
     * Convert points into its respective average grade
     * based on the grading system.
     * @return String average
     */
    public String computePoints(int points){
        String average = null;
        if(points==80){
            average = String.valueOf(1.0);
        }
        else if(points >= 75){
            average = String.valueOf(1.5);
        }
        else if(points >= 65){
            average = String.valueOf(2.0);
        }
        else if(points >= 60){
            average = String.valueOf(2.5);
        }
        else if(points >= 55){
            average = String.valueOf(3.0);
        }
        else{
            average = String.valueOf(5.0);
        }
        return average;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==nextButton){
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
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==nextButton){
            nextButton.setIcon(new ImageIcon(getClass().getResource("resources/nextHover.png")));
            nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==nextButton){
            nextButton.setIcon(new ImageIcon(getClass().getResource("resources/nextButton.png")));
            nextButton.setCursor(null);
        }
    }
}
