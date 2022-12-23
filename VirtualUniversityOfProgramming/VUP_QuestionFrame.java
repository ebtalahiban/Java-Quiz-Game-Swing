/**
 * Displays the multiple choice question and
 * allows the player to input their answer.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
@SuppressWarnings("ALL")
public class VUP_QuestionFrame extends JFrame implements MouseListener {
    String playerName;
    int currentLevel;
    int totalPoints;
    int mistakes;
    boolean passSelected, addTimeSelected;
    boolean lastQuestion = false;
    JLabel theoreticalBG, programmingBG, timerLabel, viewPoints;
    JLayeredPane pane;
    JTextPane textPane;
    JScrollPane scrollPane;
    private JButton choiceA, choiceB, choiceC, choiceD, passButton, addTimeButton, quitButton;
    StyledDocument doc;
    SimpleAttributeSet center, left, letterAttSet, snippetAttSet;

    VUP_EditQuestions editQuestions;
    VUP_Sounds clip;
    VUP_DoneQuestions doneQuestions;
    ArrayList<String> choiceList = new ArrayList<>();
    String choice;
    int category_id;
    int seconds = 15;
    int maxMistakes = 5;
    String line = "--------------------------------------------------------";
    Random random = new Random();
    /**
     * Set up 15 seconds countdown timer
     */
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            timerLabel.setText(String.valueOf(seconds));
            if(seconds<=0) {
                checkAnswer("");
            }
        }
    });

    VUP_QuestionFrame(String name, VUP_Sounds clip, int level, int category_id, int points, int countWrong, boolean pass, boolean addTime, VUP_DoneQuestions doneQuestions){
        editQuestions = new VUP_EditQuestions(category_id, doneQuestions);
        this.clip = clip;
        this.doneQuestions = doneQuestions;
        this.category_id = category_id;
        this.playerName = name;
        this.currentLevel = level;
        this.totalPoints = points;
        this.mistakes = countWrong;
        this.passSelected = pass;
        this.addTimeSelected = addTime;
        int SCREEN_WIDTH = 1060;
        int SCREEN_HEIGHT = 660;

        pane = new JLayeredPane();
        pane.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        theoreticalBG = new JLabel(new ImageIcon(getClass().getResource("resources/theoreticalBG.png")));
        theoreticalBG.setVerticalAlignment(JLabel.CENTER);
        theoreticalBG.setHorizontalAlignment(JLabel.CENTER);
        theoreticalBG.setFocusable(false);

        programmingBG = new JLabel(new ImageIcon(getClass().getResource("resources/programmingBG.png")));
        programmingBG.setVerticalAlignment(JLabel.CENTER);
        programmingBG.setHorizontalAlignment(JLabel.CENTER);
        programmingBG.setFocusable(false);

        timerLabel = new JLabel("15");
        timerLabel.setBounds(605, 42, 75, 75);
        timerLabel.setFont(new Font("",Font.BOLD, 33));
        timerLabel.setForeground(Color.RED);
        timerLabel.setOpaque(false);

        viewPoints = new JLabel();
        viewPoints.setText(""+totalPoints);
        viewPoints.setBounds(915, 90, 75, 75);
        viewPoints.setFont(new Font("",Font.BOLD, 50));
        viewPoints.setHorizontalAlignment(JLabel.CENTER);

        textPane = new JTextPane();
        textPane.setBounds(90, 130, 725, 330);
        textPane.setEditable(false);
        textPane.setFont(new Font("Comic Sans MS",Font.BOLD, 19));
        textPane.setForeground(Color.WHITE);
        textPane.setOpaque(false);

        scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(90, 130, 725, 330);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        doc = textPane.getStyledDocument();

        center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

        snippetAttSet = new SimpleAttributeSet();
        StyleConstants.setForeground(snippetAttSet, Color.green);
        StyleConstants.setFontSize(snippetAttSet,17);
        StyleConstants.setFontFamily(snippetAttSet, "Monospaced");

        left = new SimpleAttributeSet();
        StyleConstants.setForeground(left, Color.white);
        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);

        letterAttSet = new SimpleAttributeSet();
        StyleConstants.setFontFamily(letterAttSet, "Comic Sans MS");
        StyleConstants.setFontSize(letterAttSet,15);

        choiceA = new JButton();
        choiceA.setBounds(60, 535, 150, 50);
        choiceA.setIcon(new ImageIcon(getClass().getResource("resources/choiceA.png")));
        choiceA.setFocusable(true);
        choiceA.setContentAreaFilled(false);
        choiceA.setBorderPainted(false);
        choiceA.addMouseListener(this);

        choiceB = new JButton();
        choiceB.setBounds(268, 535, 150, 50);
        choiceB.setIcon(new ImageIcon(getClass().getResource("resources/choiceB.png")));
        choiceB.setFocusable(true);
        choiceB.setContentAreaFilled(false);
        choiceB.setBorderPainted(false);
        choiceB.addMouseListener(this);

        choiceC = new JButton();
        choiceC.setBounds(483, 535, 150, 50);
        choiceC.setIcon(new ImageIcon(getClass().getResource("resources/choiceC.png")));
        choiceC.setFocusable(true);
        choiceC.setContentAreaFilled(false);
        choiceC.setBorderPainted(false);
        choiceC.addMouseListener(this);

        choiceD = new JButton();
        choiceD.setBounds(698, 535, 150, 50);
        choiceD.setIcon(new ImageIcon(getClass().getResource("resources/choiceD.png")));
        choiceD.setFocusable(true);
        choiceD.setContentAreaFilled(false);
        choiceD.setBorderPainted(false);
        choiceD.addMouseListener(this);

        passButton = new JButton();
        passButton.setBounds(915, 320, 75, 75);
        passButton.setIcon(new ImageIcon(getClass().getResource("resources/cheatPass.png")));
        passButton.setContentAreaFilled(false);
        passButton.setBorderPainted(false);
        passButton.setEnabled(passSelected);
        passButton.addMouseListener(this);

        addTimeButton = new JButton();
        addTimeButton.setBounds(915, 410, 75, 75);
        addTimeButton.setIcon(new ImageIcon(getClass().getResource("resources/cheatTime.png")));
        addTimeButton.setContentAreaFilled(false);
        addTimeButton.setBorderPainted(false);
        addTimeButton.setEnabled(addTimeSelected);
        addTimeButton.addMouseListener(this);

        quitButton = new JButton();
        quitButton.setBounds(10, 10, 40, 40);
        quitButton.setIcon(new ImageIcon(getClass().getResource("resources/settingsIcon.png")));
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
        if(category_id==0){
            this.add(theoreticalBG);
        } else{
            this.add(programmingBG);
        }
        pane.add(timerLabel);
        pane.add(viewPoints);
        pane.add(scrollPane);
        pane.add(choiceA);
        pane.add(choiceB);
        pane.add(choiceC);
        pane.add(choiceD);
        pane.add(passButton);
        pane.add(addTimeButton);
        pane.add(quitButton);

        start();
    }

    public void start(){
        displayMCQ();
        timer.start();
    }
    /**
     * Displays the question, choices and snippet if there are any.
     */
    public void displayMCQ(){
        textPane.setText(editQuestions.getQuestion()+"\n");
        try {
            if(category_id == 1){
                doc.setParagraphAttributes(doc.getLength(),1 , center, false);
                doc.insertString(doc.getLength(), editQuestions.getSnippet(), snippetAttSet );
                doc.insertString(doc.getLength(), "\n\n", null );
            }
            doc.setParagraphAttributes(doc.getLength(),1 , left, false);
            String choices[] = new String[4];
            choices = editQuestions.getChoices();
            doc.insertString(doc.getLength(),
                        line+"\n"+
                        "CHOICE A:\n"+choices[0] + "\n"+ line +"\n" +
                        "CHOICE B:\n"+choices[1] + "\n"+ line +"\n" +
                        "CHOICE C:\n"+choices[2] + "\n"+ line +"\n" +
                        "CHOICE D:\n"+choices[3] + "\n"+ line,
                    letterAttSet );
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        textPane.setCaretPosition(0);
    }
    /**
     * Open lose frame when game over
     */
    public void gameOver(){
        timer.stop();
        clip.stop();
        new VUP_LoseFrame(playerName, clip, doneQuestions);
        this.dispose();
    }
    /**
     * Check of the chosen answer
     * matches the correct asnwer
     */
    public void checkAnswer(String choice){
        timer.stop();
        if(choice.equals(editQuestions.getLetterAnswer())){
            clip.startSound(5);
            totalPoints+=5;
            continueGame();
        }
        else{
            clip.startSound(6);
            mistakes++;
            if(mistakes<=maxMistakes){
                continueGame();
            }
            else{
                //game over
                gameOver();
            }
        }
    }
    /**
     * Continue the game if answer is correct
     * or number of mistake is less than six
     */
    public void continueGame(){
        if(category_id==0){
            new VUP_QuestionFrame(playerName, clip, currentLevel, category_id+1, totalPoints, mistakes, passSelected, addTimeSelected, doneQuestions);
        }
        else{
            if(currentLevel==8){
                lastQuestion = true;
            }
            currentLevel++;
            if(lastQuestion){
                // win
                clip.stop();
                new VUP_WinFrame(playerName, totalPoints, clip);
            }
            else{
                clip.stop();
                clip.startMusic(1);
                new VUP_LevelsFrame(playerName, clip, currentLevel, totalPoints, mistakes, passSelected, addTimeSelected, doneQuestions);
            }
        }
        timer.stop();
        this.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==choiceA){
            clip.startSound(7);
            choice = "A";
            checkAnswer(choice);
        }
        if(e.getSource()==choiceB){
            clip.startSound(7);
            choice = "B";
            checkAnswer(choice);
        }
        if(e.getSource()==choiceC){
            clip.startSound(7);
            choice = "C";
            checkAnswer(choice);
        }
        if(e.getSource()==choiceD){
            clip.startSound(7);
            choice = "D";
            checkAnswer(choice);
        }
        /**
         * Generate new question
         */
        if(e.getSource()==passButton && passButton.isEnabled()){
            clip.startSound(7);
            passSelected = false;
            passButton.setEnabled(passSelected);
            timer.stop();
            new VUP_QuestionFrame(playerName, clip, currentLevel, category_id, totalPoints, mistakes, passSelected, addTimeSelected, doneQuestions);
            this.dispose();
        }
        /**
         * Add 10 seconds to the on going timer
         */
        if(e.getSource()==addTimeButton && addTimeButton.isEnabled()){
            clip.startSound(7);
            seconds += 10;
            addTimeSelected = false;
            addTimeButton.setEnabled(addTimeSelected);
        }
        /**
         * Exit the game play and redirect to main menu
         */
        if(e.getSource()==quitButton){
            clip.startSound(7);
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?\n(Your current progress will not be saved.)", "Notice", 0);
            if (result == JOptionPane.YES_OPTION){
                timer.stop();
                clip.stop();
                clip.startMusic(1);
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
        if(e.getSource()==choiceA){
            choiceA.setIcon(new ImageIcon(getClass().getResource("resources/choiceAHover.png")));
            choiceA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==choiceB){
            choiceB.setIcon(new ImageIcon(getClass().getResource("resources/choiceBHover.png")));
            choiceB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==choiceC){
            choiceC.setIcon(new ImageIcon(getClass().getResource("resources/choiceCHover.png")));
            choiceC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==choiceD){
            choiceD.setIcon(new ImageIcon(getClass().getResource("resources/choiceDHover.png")));
            choiceD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==passButton){
            passButton.setIcon(new ImageIcon(getClass().getResource("resources/passHover.png")));
            passButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==addTimeButton){
            addTimeButton.setIcon(new ImageIcon(getClass().getResource("resources/timeHover.png")));
            addTimeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if(e.getSource()==quitButton){
            quitButton.setIcon(new ImageIcon(getClass().getResource("resources/settingsIconHover.png")));
            quitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==choiceA){
            choiceA.setIcon(new ImageIcon(getClass().getResource("resources/choiceA.png")));
            choiceA.setCursor(null);
        }
        if(e.getSource()==choiceB){
            choiceB.setIcon(new ImageIcon(getClass().getResource("resources/choiceB.png")));
            choiceB.setCursor(null);
        }
        if(e.getSource()==choiceC){
            choiceC.setIcon(new ImageIcon(getClass().getResource("resources/choiceC.png")));
            choiceC.setCursor(null);
        }
        if(e.getSource()==choiceD){
            choiceD.setIcon(new ImageIcon(getClass().getResource("resources/choiceD.png")));
            choiceD.setCursor(null);
        }
        if(e.getSource()==passButton){
            passButton.setIcon(new ImageIcon(getClass().getResource("resources/cheatPass.png")));
            passButton.setCursor(null);
        }
        if(e.getSource()==addTimeButton){
            addTimeButton.setIcon(new ImageIcon(getClass().getResource("resources/cheatTime.png")));
            addTimeButton.setCursor(null);
        }
        if(e.getSource()==quitButton){
            quitButton.setIcon(new ImageIcon(getClass().getResource("resources/settingsIcon.png")));
            quitButton.setCursor(null);
        }
    }
}
