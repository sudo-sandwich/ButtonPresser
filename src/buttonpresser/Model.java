/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttonpresser;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Model {

    private int currentButton;
    private ArrayList<ArrayList<Color>> buttons;
    private ArrayList<Boolean> buttonTypes;

    public SimpleIntegerProperty score = new SimpleIntegerProperty(0);

    public SimpleDoubleProperty timeLeft = new SimpleDoubleProperty(0);
    public static final double STARTTIME = 3;

    Random rand;

    public enum Color {
        RED, GREEN, BLUE;

        private static final Color[] VALUES = values();
        private static final int SIZE = VALUES.length;
        private static final Random RANDOM = new Random();

        public static Color randomColor() {
            return VALUES[RANDOM.nextInt(SIZE)];
        }
    }

    public Model() {
        rand = new Random();
        buttons = new ArrayList();
        for (int i = 0; i < 3; i++) {
            ArrayList<Color> newArray = new ArrayList();
            newArray.add(null);
            newArray.add(null);
            buttons.add(newArray);
        }
        buttonTypes = new ArrayList(2);
        buttonTypes.add(null);
        buttonTypes.add(null);
    }

    public void reset() {
        for (int i = 0; i < 2; i++) {
            ArrayList<Color> randomColors = randomRGBColorArray();
            for (int j = 0; j < 3; j++) {
                buttons.get(j).set(i, randomColors.get(j));
            }
        }

        for (int i = 0; i < 2; i++) {
            buttonTypes.set(i, rand.nextBoolean());
        }
        currentButton = rand.nextInt(3);
    }
    
    public void timerExpired(int amountToDecrease) {
        decreaseScore(amountToDecrease);
    }

    public static ArrayList<Color> randomRGBColorArray() {
        ArrayList<Color> colors = new ArrayList();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        Collections.shuffle(colors);
        return colors;
    }

    public static ArrayList<Color> randomRGBColorArray(Color exclude) {
        ArrayList<Color> colors = randomRGBColorArray();
        colors.remove(exclude);
        return colors;
    }

    public boolean isCorrectButton(int buttonid) {
        return currentButton == buttonid;
    }

    public int getCurrentButtonId() {
        return currentButton;
    }

    public ArrayList<ArrayList<Color>> getAllButtons() {
        return buttons;
    }

    public ArrayList<Color> getButton(int buttonid) {
        return buttons.get(buttonid);
    }

    public ArrayList<Color> getCurrentButton() {
        return buttons.get(currentButton);
    }

    public ArrayList<Boolean> getButtonTypes() {
        return buttonTypes;
    }

    public void increaseScore(int amount) {
        score.set(score.get() + amount);
    }
    public void decreaseScore(int amount) {
        score.set(score.get() - amount);
    }
}
