/**
 * Displays a clickable email icon.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
@SuppressWarnings("ALL")
public class VUP_ClickEmail extends JFrame implements MouseListener {
    VUP_Sounds clip;
    JButton emailButton;

    VUP_ClickEmail(VUP_Sounds clip){
        this.clip = clip;
        int SCREEN_WIDTH = 1060;
        int SCREEN_HEIGHT = 660;

        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("resources/emailBG.gif")));
        background.setVerticalAlignment(JLabel.CENTER);
        background.setHorizontalAlignment(JLabel.CENTER);

        emailButton = new JButton();
        emailButton.setBounds(435, 110, 195, 155);
        emailButton.setContentAreaFilled(false);
        emailButton.setBorderPainted(false);
        emailButton.addMouseListener(this);

        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Virtual University of Programming");
        this.setIconImage(new ImageIcon(getClass().getResource("resources/Logo.png")).getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(pane);
        this.add(background);
        pane.add(emailButton);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // redirect to next frame when email icon is clicked
        if(e.getSource()==emailButton){
            clip.startSound(7);
            new VUP_ConfirmAdmission(clip);
            this.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==emailButton){
            emailButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==emailButton){
            emailButton.setCursor(null);
        }
    }
}
