/**
 * Gets a random and unique question from
 * the fetched questions data.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Random;
public class VUP_EditQuestions {
    VUP_ReadQuestions readQuestions;
    VUP_DoneQuestions doneQuestions;
    ArrayList<Integer> id;
    ArrayList<String> questions;
    ArrayList<String> choice1;
    ArrayList<String> choice2;
    ArrayList<String> choice3;
    ArrayList<String> choice4;
    ArrayList<String> correctAnswer;
    ArrayList<String> snippets;
    String currentQuestion;
    String snippet;
    String answer;
    String letterAnswer;
    String[] choices = new String[4];
    Random random;
    int category;
    int questionSize;
    int question_id;
    /**
     * Initialize the fetched questions based from the category id.
     */
    VUP_EditQuestions(int category_id, VUP_DoneQuestions doneQuestions){
        this.category = category_id;
        this.doneQuestions = doneQuestions;
        readQuestions = new VUP_ReadQuestions(category_id);
        id = readQuestions.getID();
        questions = readQuestions.getQuestions();
        choice1 = readQuestions.getChoice1();
        choice2 = readQuestions.getChoice2();
        choice3 = readQuestions.getChoice3();
        choice4 = readQuestions.getChoice4();
        correctAnswer = readQuestions.getCorrectAnswer();
        snippets = readQuestions.getSnippets();
        random = new Random();
        questionSize = questions.size()-1;
        setRandomID();
    }

    /**
     * Generate random and unique question id.
     * Add each unique question id to doneQuestions
     */
    public void setRandomID(){
        question_id = random.nextInt(questionSize);
        if(doneQuestions.isNull(category)){
            doneQuestions.addToAskedQuestions(category, question_id);
            setQuestion(question_id);
        }
        else if(doneQuestions.isDone(category, question_id)){
            do{
                question_id = random.nextInt(questionSize);
            }
            while(doneQuestions.isDone(category, question_id));
            doneQuestions.addToAskedQuestions(category, question_id);
            setQuestion(question_id);
        }
        else{
            doneQuestions.addToAskedQuestions(category, question_id);
            setQuestion(question_id);
        }
    }
    /**
     * Set the chosen question and its respective data
     * to be displayed in the question frame
     */
    public void setQuestion(int question_id){
        currentQuestion = questions.get(question_id);
        setChoices(question_id);
        setCorrectAnswer(question_id);
        if(category==1){
            setSnippet(question_id);
        }
    }
    public String getQuestion(){
        return currentQuestion;
    }
    public void setSnippet(int question_id){
        snippet = snippets.get(question_id);
    }
    public String getSnippet(){
        return snippet;
    }
    public void setChoices(int question_id){
        ArrayList<String> choiceList = new ArrayList<>();
        choiceList.add(choice1.get(question_id));
        choiceList.add(choice2.get(question_id));
        choiceList.add(choice3.get(question_id));
        choiceList.add(choice4.get(question_id));
        int i = 0;
        int index;

        for(int size = choiceList.size(); size > 0; size--){
            index = random.nextInt(size);
            choices[i] = choiceList.get(index);
            choiceList.remove(index);
            i++;
        }
    }
    public String[] getChoices(){
        return choices;
    }

    public void setCorrectAnswer(int question_id){
        this.answer = correctAnswer.get(question_id);
        setLetterAnswer(choices);
    }
    public String getCorrectAnswer(){
        return answer;
    }
    public void setLetterAnswer(String[] choices){
        for(int i=0; i<choices.length; i++){
            if(choices[i].equals(getCorrectAnswer())){
                switch (i) {
                    case 0 -> letterAnswer = "A";
                    case 1 -> letterAnswer = "B";
                    case 2 -> letterAnswer = "C";
                    case 3 -> letterAnswer = "D";
                }
            }
        }
    }
    public String getLetterAnswer(){
        return letterAnswer;
    }
}
