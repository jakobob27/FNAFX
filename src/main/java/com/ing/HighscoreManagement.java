package com.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HighscoreManagement {
    private static ArrayList<String> scores = new ArrayList<String>();

    public static void writeToFile(HighscoreEntry h){
        File scoreFile = new File("highscore.dat");
        if (!scoreFile.exists()){
            try {
                scoreFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter writeFile = null;
        BufferedWriter writer = null;
        BufferedReader reader = null;
        
        
        try {
            writeFile = new FileWriter(scoreFile,true);
            writer = new BufferedWriter(writeFile);
            
            scores.add(h.toString());
            Collections.sort(scores);
            Collections.reverse(scores);
            if (scores.size() == 11){
                scores.remove(10);
        
            }
            System.out.println(scores);
        

            FileWriter fw = new FileWriter(scoreFile, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();

            for (String string : scores) {
                writer.write(string);
                writer.newLine();
            }  
        }
        catch (Exception e){
    
        }
        finally {
            try {
                if (writer != null){
                    writer.close();
                }
                if (reader != null){
                    reader.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }
    public static void updateScore(){
        File scoreFile = new File("highscore.dat");
        if (!scoreFile.exists()){
            try {
                scoreFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(scoreFile));
            String currentLine = reader.readLine();
            
            while (currentLine != null)
            {
                scores.add(currentLine);
                currentLine = reader.readLine();
            }

            Collections.sort(scores);
            Collections.reverse(scores);
            
            System.out.println(scores);
    
        }
        catch (Exception e){
    
        }

        finally {
            try {
                if (reader != null){
                    reader.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }


    public static int getHighscore() {
        if (scores.size() == 0){
            return 0;
        }
        String s = scores.get(0);
        String t = s.substring(0,1);
        return Integer.parseInt(t);
    }
    public static ObservableList<HighscoreEntry> getHighscoreList(){
        List<HighscoreEntry> lst = new ArrayList<>();
        for (String string : scores) {
            String[] s = string.split(",");
            HighscoreEntry score = new HighscoreEntry(Integer.parseInt(s[0]), s[1], s[2]);
            lst.add(score);
        }
        
        ObservableList<HighscoreEntry> d = FXCollections.observableList(lst);
        return d;
    }
}

    
