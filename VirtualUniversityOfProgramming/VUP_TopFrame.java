/**
 * Displays the default/updated set of the top 10 player names and their grades.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
@SuppressWarnings("ALL")
public class VUP_TopFrame extends JFrame implements MouseListener {
    String playerName;
    JButton backButton;
    JLabel nameLabel, scoreLabel, names, scores;
    VUP_Sounds clip;

    VUP_TopFrame(String name, VUP_Sounds clip){
        this.playerName = name;
        this.clip = clip;
        int SCREEN_WIDTH = 1060;
        int SCREEN_HEIGHT = 660;

        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("resources/topBG.png")));
        background.setVerticalAlignment(JLabel.CENTER);
        background.setHorizontalAlignment(JLabel.CENTER);

        nameLabel = new JLabel();
        nameLabel.setText("Name");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setHorizontalTextPosition(JLabel.CENTER);
        nameLabel.setFont(new Font("Monospaced", Font.PLAIN, 35));
        nameLabel.setBounds(50, 150, 300, 45);

        scoreLabel = new JLabel();
        scoreLabel.setText("Score");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setHorizontalTextPosition(JLabel.CENTER);
        scoreLabel.setFont(new Font("Monospaced", Font.PLAIN, 35));
        scoreLabel.setBounds(350, 150, 300, 45);

        names = new JLabel();
        names.setForeground(Color.WHITE);
        names.setHorizontalAlignment(JLabel.CENTER);
        names.setHorizontalTextPosition(JLabel.CENTER);
        names.setFont(new Font("Monospaced", Font.PLAIN, 20));
        names.setBounds(50, 170, 300, 285);

        scores = new JLabel();
        scores.setForeground(Color.WHITE);
        scores.setHorizontalAlignment(JLabel.CENTER);
        scores.setHorizontalTextPosition(JLabel.CENTER);
        scores.setFont(new Font("Monospaced", Font.PLAIN, 20));
        scores.setBounds(350, 170, 300, 285);

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
        pane.add(nameLabel);
        pane.add(scoreLabel);
        pane.add(names);
        pane.add(scores);
        try {
            getScores();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Copies the existing highscores.txt file from inside the jar/exe file
     * into the same directory of the jar/exe.
     * @return String path of the highscores.txt file
     */
    public String ExportResource(String resourceName) throws Exception {
        String path = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
        File file = new File(path+resourceName);
        String jarFolder = path;
        if(!file.exists()){
            InputStream stream = null;
            OutputStream resStreamOut = null;
            try {
                stream = this.getClass().getResourceAsStream("/highscores.txt");
                if(stream == null) {
                    throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
                }
                int readBytes;
                byte[] buffer = new byte[4096];
                jarFolder = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
                resStreamOut = new FileOutputStream(jarFolder + resourceName);
                while ((readBytes = stream.read(buffer)) > 0) {
                    resStreamOut.write(buffer, 0, readBytes);
                }
            } catch (Exception ex) {
                throw ex;
            } finally {
                stream.close();
                resStreamOut.close();
            }
        }
        return jarFolder + resourceName;
    }

    /**
     * Reads the data (name and gardes) inside the highscores.txt file.
     */
    public void getScores() throws Exception {
        String fullPath = ExportResource("/highscores.txt");
        File fileObject = new File(fullPath);
        FileReader fReader;
        BufferedReader bReader;

        String[] lines;
        String[] name;
        float[] score;

        try {
            if(!fileObject.exists()){
                System.out.println("No such file score.txt");
            }
            else{
                fReader = new FileReader(fileObject);
                bReader = new BufferedReader(fReader);

                int count = countLines(fileObject);
                name = new String[count];
                score = new float[count];
                String currLine;

                int i = 0;
                while((currLine=bReader.readLine()) != null){
                    lines = currLine.split(" ");
                    name[i] = lines[0];
                    score[i] = Float.parseFloat(lines[1]);
                    i++;
                }
                printScores(name, score);
            }
        } catch (IOException ex) {
            System.out.println("Error opening the file score.txt");
        }
    }

    public int countLines(File fileObject){
        FileReader fReader;
        BufferedReader bReader;
        int count = 0;
        try {
            if(!fileObject.exists()){
                System.out.println("No such file score.txt");
            }
            else{
                fReader = new FileReader(fileObject);
                bReader = new BufferedReader(fReader);
                while(bReader.readLine() != null){
                    count++;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error opening the file score.txt");
        }
        return count;
    }
    /**
     * Display the content of the highscores.txt file.
     */
    public void printScores(String[] name, float[] score){
        for (int n = 0; n < score.length; n++) {
            for (int m = 0; m < (score.length-1) - n; m++) {
                if (score[m] > score[m+1]) {
                    float swapInt = score[m];
                    score[m] = score[m + 1];
                    score[m + 1] = swapInt;
                    String swapString = name[m];
                    name[m] = name[m + 1];
                    name[m + 1] = swapString;
                }
            }
        }
        StringBuffer sbNames = new StringBuffer("<html>");
        StringBuffer sbScores = new StringBuffer("<html>");
        StringBuffer sbName;
        int i = 1;
        int index;
        for (int n = 0; n < 10; n++) {
            sbNames.append("<br/>");
            sbNames.append(name[n]);
            index = sbNames.length()-name[n].length();
            sbNames.insert(index, i+". ");
            i++;

            sbScores.append("<br/>");
            sbScores.append(score[n]);
        }
        sbNames.append("</html>");
        sbScores.append("</html>");
        names.setText(String.valueOf(sbNames));
        scores.setText(String.valueOf(sbScores));
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
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {  }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==backButton){
            backButton.setIcon(new ImageIcon(getClass().getResource("resources/backHover.png")));
            backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        backButton.setIcon(new ImageIcon(getClass().getResource("resources/backButton.png")));
        backButton.setCursor(null);
    }
}
