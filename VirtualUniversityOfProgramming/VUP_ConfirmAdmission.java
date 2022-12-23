/**
 * Asks for the name/nickname of the current player.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
@SuppressWarnings("ALL")
public class VUP_ConfirmAdmission extends JFrame implements MouseListener {
    String playerName;
    JTextField inputName;
    JButton acceptButton, declineButton;
    VUP_Sounds clip, sfx = new VUP_Sounds();

    VUP_ConfirmAdmission(VUP_Sounds clip){
        this.clip = clip;
        int SCREEN_WIDTH = 1060;
        int SCREEN_HEIGHT = 660;

        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("resources/admissionBG.png")));
        background.setVerticalAlignment(JLabel.CENTER);
        background.setHorizontalAlignment(JLabel.CENTER);

        inputName = new JTextField();
        inputName.setText("Enter your name");
        inputName.setBounds(235, 220, 580, 70);
        inputName.setHorizontalAlignment(JTextField.CENTER);
        inputName.setForeground(Color.GRAY);
        inputName.setFont(new Font("Arial Rounded MT Bold", 0, 35));
        inputName.setBorder(null);
        inputName.setOpaque(false);
        inputName.addMouseListener(this);

        acceptButton = new JButton();
        acceptButton.setBounds(600, 520, 155, 50);
        acceptButton.setIcon(new ImageIcon(getClass().getResource("resources/acceptButton.png")));
        acceptButton.setContentAreaFilled(false);
        acceptButton.setBorderPainted(false);
        acceptButton.addMouseListener(this);

        declineButton = new JButton();
        declineButton.setBounds(790, 520, 155, 50);
        declineButton.setIcon(new ImageIcon(getClass().getResource("resources/declineButton.png")));
        declineButton.setContentAreaFilled(false);
        declineButton.setBorderPainted(false);
        declineButton.addMouseListener(this);

        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Virtual University of Programming");
        this.setIconImage(new ImageIcon(getClass().getResource("resources/Logo.png")).getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(pane);
        this.add(background);
        pane.add(inputName);
        pane.add(acceptButton);
        pane.add(declineButton);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==inputName){
            inputName.setText("");
            inputName.setForeground(Color.BLACK);
        }
        /**
         * Save the player name and pass it to the next frame (main frame).
         */
        if(e.getSource()==acceptButton){
            clip.startSound(7);
            if(inputName.getText().equals("") || inputName.getText().equals("Enter your name") || inputName.getText().isBlank()){
                JOptionPane.showMessageDialog(this, "Please enter your name.","Invalid name input", JOptionPane.ERROR_MESSAGE);
            }
            else {
                playerName = inputName.getText();
                new VUP_MainFrame(playerName, clip);
                this.dispose();
            }
        }
        if(e.getSource()==declineButton){
            clip.startSound(7);
            int result = JOptionPane.showConfirmDialog(null, "Declining the admission will exit the game.\n Are you sure you want to exit?", "Notice", 0);
            if (result == JOptionPane.YES_OPTION) {
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
        if(e.getSource()==acceptButton){
            acceptButton.setIcon(new ImageIcon(getClass().getResource("resources/acceptHover.png")));
            acceptButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==declineButton){
            declineButton.setIcon(new ImageIcon(getClass().getResource("resources/declineHover.png")));
            declineButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==acceptButton){
            acceptButton.setIcon(new ImageIcon(getClass().getResource("resources/acceptButton.png")));
            acceptButton.setCursor(null);
        }
        if(e.getSource()==declineButton){
            declineButton.setIcon(new ImageIcon(getClass().getResource("resources/declineButton.png")));
            declineButton.setCursor(null);
        }
    }
}
