/**
 * Responsible for fetching and reading the questions
 * from the questionsFile.xlsx based on the category id.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@SuppressWarnings("ALL")
public class VUP_ReadQuestions {
    ArrayList<String> header = new ArrayList<>();
    ArrayList<Integer> id = new ArrayList<>();
    ArrayList<String> topic = new ArrayList<>();
    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> choice1 = new ArrayList<>();
    ArrayList<String> choice2 = new ArrayList<>();
    ArrayList<String> choice3 = new ArrayList<>();
    ArrayList<String> choice4 = new ArrayList<>();
    ArrayList<String> correctAnswer = new ArrayList<>();
    ArrayList<String> snippets = new ArrayList<>();
    File questionsFile = new File("questionsFile.xlsx");
    String path = questionsFile.getAbsoluteFile().getParent() + "/questionsFile.xlsx";
    DataFormatter formatter;
    String cellContent;
    /**
     *  Access the questionsFile from inside the jar/exe.
     */
    public VUP_ReadQuestions(int category_id){
        formatter = new DataFormatter();
        try( InputStream inputStream = this.getClass().getResourceAsStream("/questionsFile.xlsx")){
            XSSFWorkbook importedFile = new XSSFWorkbook(inputStream);
            XSSFSheet theoretical = importedFile.getSheetAt(category_id);
            for (Row row : theoretical) {
                Iterator<Cell> TcellIterator = row.cellIterator();
                while (TcellIterator.hasNext()) {
                    Cell cell = TcellIterator.next();
                    if (row.getRowNum() == 0) {
                        header.add(cell.getStringCellValue());
                    } else {
                        switch (cell.getColumnIndex()) {
                            case 0 -> id.add((int) cell.getNumericCellValue());
                            case 1 -> topic.add(cell.getStringCellValue());
                            case 2 -> questions.add(cell.getStringCellValue());
                            case 3 -> {
                                cellContent = formatter.formatCellValue(cell);
                                choice1.add(cellContent);
                            }
                            case 4 -> {
                                cellContent = formatter.formatCellValue(cell);
                                choice2.add(cellContent);
                            }
                            case 5 -> {
                                cellContent = formatter.formatCellValue(cell);
                                choice3.add(cellContent);
                            }
                            case 6 -> {
                                cellContent = formatter.formatCellValue(cell);
                                choice4.add(cellContent);
                            }
                            case 7 -> {
                                cellContent = formatter.formatCellValue(cell);
                                correctAnswer.add(cellContent);
                            }
                            case 8 -> {
                                cellContent = formatter.formatCellValue(cell);
                                snippets.add(cellContent);
                            }
                        }
                    }
                }
            }
        } catch(IOException ex){
            JOptionPane.showMessageDialog(null, "File Not Found. Re-check the questionsFile.xlsx file");
        }
    }
    /**
     * Store question data in arraylists
     */
    public ArrayList<String> getQuestions(){
        return questions;
    }
    public ArrayList<String> getChoice1(){
        return choice1;
    }
    public ArrayList<String> getChoice2(){
        return choice2;
    }
    public ArrayList<String> getChoice3(){
        return choice3;
    }
    public ArrayList<String> getChoice4(){
        return choice4;
    }
    public ArrayList<String> getCorrectAnswer(){
        return correctAnswer;
    }
    public ArrayList<String> getSnippets(){
        return snippets;
    }
    public ArrayList<Integer> getID(){
        return id;
    }
}
