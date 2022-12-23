/**
 * Updates/Saves the data inside the highscores.txt file.
 *
 * @author  Erica Talahiban
 * @version 1.0
 */

import java.io.*;
public class VUP_TopStudents {
    VUP_TopStudents(String name, float score){
        try {
            saveScore(name,score);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Adds the name and grade of the player to the highscores.txt.
     */
    public void saveScore(String playerName, float finalGrade) throws Exception {
        String fullPath = ExportResource("/highscores.txt");
        File fileObject = new File(fullPath);
        try{
            FileWriter fw = new FileWriter(fileObject, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println();
            pw.print(playerName+" "+finalGrade);
            pw.flush();
            pw.close();
            bw.close();
            fw.close();
        }catch(IOException e){
            System.out.println("Error opening the file score.txt");
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
            } finally {
                assert stream != null;
                stream.close();
                assert resStreamOut != null;
                resStreamOut.close();
            }
        }
        return jarFolder + resourceName;
    }
}
