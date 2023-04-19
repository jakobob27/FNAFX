package com.ing.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ing.HighscoreEntry;
import com.ing.HighscoreManagement;

public class testHighscore {
    HighscoreEntry h1;
    ArrayList<String> scores;

    @SuppressWarnings("unchecked")
    @BeforeEach
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        Field scoresField = HighscoreManagement.class.getDeclaredField("scores");
        scoresField.setAccessible(true);
        scores = (ArrayList<String>) scoresField.get(null);
        HighscoreEntry h1 = new HighscoreEntry(3, "Herman");
        HighscoreEntry h2 = new HighscoreEntry(2, "Jakob");
        HighscoreEntry h3 = new HighscoreEntry(1, "X");
        scores.add(h1.toString());
        scores.add(h2.toString());
        scores.add(h3.toString());
    }

    @Test
    public void testgetHighscore() {
        assertEquals(3, HighscoreManagement.getHighscore());
    }

    @Test
    public void testgetHighscoreList() {
        assertEquals(scores.get(0).toString(), HighscoreManagement.getHighscoreList().get(0).toString());
    }

}
