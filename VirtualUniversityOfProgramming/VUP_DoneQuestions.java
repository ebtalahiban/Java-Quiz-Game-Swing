/**
 * Stores the question id of the questions that were
 * already asked.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */

import java.util.ArrayList;
public class VUP_DoneQuestions {
    ArrayList<Integer> AskedQuestions;
    ArrayList<Integer> TheoreticalQuestions;
    ArrayList<Integer> ProgrammingQuestions;
    VUP_DoneQuestions(){
        AskedQuestions = new ArrayList<>();
        TheoreticalQuestions = new ArrayList<>();
        ProgrammingQuestions = new ArrayList<>();
    }
    /**
     * Check if the arraylists are empty
     */
    public boolean isNull(int category){
        if(category==0){
            return TheoreticalQuestions.size() <= 0;
        }
        else{
            return ProgrammingQuestions.size() <= 0;
        }
    }
    /**
     * Store the chosen question to the arraylists
     */
    public void addToAskedQuestions(int category, int id){
        AskedQuestions.add(id);
        if(category==0){
            TheoreticalQuestions.add(id);
        }
        else{
            ProgrammingQuestions.add(id);
        }
    }
    /**
     * Check if current id was already asked
     */
    public boolean isDone(int category, int id){
        if(category==0){
            return TheoreticalQuestions.contains(id);
        }
        else{
            return ProgrammingQuestions.contains(id);
        }
    }
}
