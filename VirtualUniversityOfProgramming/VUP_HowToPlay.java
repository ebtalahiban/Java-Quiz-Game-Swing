/**
 * Set of instructions on how to play the game.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
@SuppressWarnings("ALL")
public class VUP_HowToPlay extends JFrame implements MouseListener {
    VUP_Sounds clip;
    String playerName;
    String page;
    int pageNum = 1;
    JLabel background;
    JButton nextButton, prevButton, backButton;

    VUP_HowToPlay(String name, VUP_Sounds clip){
        this.playerName = name;
        this.clip = clip;
        page = "page" + String.valueOf(pageNum) + ".png";
        int SCREEN_WIDTH = 1060;
        int SCREEN_HEIGHT = 660;

        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        background = new JLabel(new ImageIcon(getClass().getResource("resources/howToPlay/"+page)));
        background.setVerticalAlignment(JLabel.CENTER);
        background.setHorizontalAlignment(JLabel.CENTER);

        nextButton = new JButton();
        nextButton.setBounds(870, 485, 25, 25);
        nextButton.setIcon(new ImageIcon(getClass().getResource("resources/nextBtn.png")));
        nextButton.setContentAreaFilled(false);
        nextButton.setBorderPainted(false);
        nextButton.addMouseListener(this);

        prevButton = new JButton();
        prevButton.setBounds(835, 485, 25, 25);
        prevButton.setIcon(new ImageIcon(getClass().getResource("resources/prevBtn.png")));
        prevButton.setContentAreaFilled(false);
        prevButton.setBorderPainted(false);
        prevButton.addMouseListener(this);

        backButton = new JButton();
        backButton.setBounds(780, 545, 225, 70);
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
        pane.add(nextButton);
        pane.add(prevButton);
        pane.add(backButton);
        setButtons(pageNum);
    }
    /**
     * Set the state of the buttons
     */
    public void setButtons(int pageNum){
        if(pageNum==1){
            prevButton.setEnabled(false);
        }else{
            prevButton.setEnabled(true);
        }
        if(pageNum==4){
            nextButton.setEnabled(false);
        }else{
            nextButton.setEnabled(true);
        }
    }
    /**
     * Change background when prevButton/nextButton is clicked
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==nextButton){
            if(nextButton.isEnabled()){
                pageNum++;
                page = "page"+String.valueOf(pageNum)+".png";
                background.setIcon(new ImageIcon(getClass().getResource("resources/howToPlay/"+page)));
                setButtons(pageNum);
            }
        }
        if(e.getSource()==prevButton){
            if(prevButton.isEnabled()){
                pageNum--;
                page = "page"+String.valueOf(pageNum)+".png";
                background.setIcon(new ImageIcon(getClass().getResource("resources/howToPlay/"+page)));
                setButtons(pageNum);
            }
        }
        if(e.getSource()==backButton){
            clip.startSound(7);
            new VUP_MainFrame(playerName, clip);
            this.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==nextButton){
            if(nextButton.isEnabled()){
                nextButton.setIcon(new ImageIcon(getClass().getResource("resources/nextBtnHover.png")));
                nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        }
        if(e.getSource()==prevButton){
            if(prevButton.isEnabled()){
                prevButton.setIcon(new ImageIcon(getClass().getResource("resources/prevHover.png")));
                prevButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        }
        if(e.getSource()==backButton){
            backButton.setIcon(new ImageIcon(getClass().getResource("resources/backHover.png")));
            backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==nextButton){
            nextButton.setIcon(new ImageIcon(getClass().getResource("resources/nextBtn.png")));
            nextButton.setCursor(null);
        }
        if(e.getSource()==prevButton){
            prevButton.setIcon(new ImageIcon(getClass().getResource("resources/prevBtn.png")));
            prevButton.setCursor(null);
        }
        backButton.setIcon(new ImageIcon(getClass().getResource("resources/backButton.png")));
        backButton.setCursor(null);
    }
}
