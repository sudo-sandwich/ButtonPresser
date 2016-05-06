/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttonpresser;

public class ColorConverter {
    
    public static javafx.scene.paint.Color convert(buttonpresser.Model.Color colorToConvert) {
        javafx.scene.paint.Color colorToReturn = null;
        switch (colorToConvert) {
            case RED:
                colorToReturn = javafx.scene.paint.Color.RED;
                break;
            case GREEN:
                colorToReturn = javafx.scene.paint.Color.GREEN;
                break;
            case BLUE:
                colorToReturn = javafx.scene.paint.Color.BLUE;
                break;
        }
        return colorToReturn;
    }
    
    public static String toString(buttonpresser.Model.Color colorToConvert) {
        String colorToReturn = null;
        switch (colorToConvert) {
            case RED:
                colorToReturn = "Red";
                break;
            case GREEN:
                colorToReturn = "Green";
                break;
            case BLUE:
                colorToReturn = "Blue";
                break;
        }
        return colorToReturn;
    }
}
