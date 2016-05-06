/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttonpresser;

import static buttonpresser.Model.STARTTIME;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public class MainUI implements Initializable {

    @FXML
    Label lblScore;
    @FXML
    Label lblTimer;
    @FXML
    Label lblTextType;
    @FXML
    Label lblMain;
    @FXML
    Label lblButtonType;
    @FXML
    Button btn0;
    @FXML
    Button btn1;
    @FXML
    Button btn2;

    private ArrayList<Button> buttons;
    private ArrayList<Label> types;

    private Model model;

    public final int BUTTONSCORE = 1;

    Timeline timer;
    public boolean isTimerExpired;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (!isTimerExpired) {
            boolean isCorrect = false;
            if (event.getSource() == btn0 && model.isCorrectButton(0)) {
                isCorrect = true;
            } else if (event.getSource() == btn1 && model.isCorrectButton(1)) {
                isCorrect = true;
            } else if (event.getSource() == btn2 && model.isCorrectButton(2)) {
                isCorrect = true;
            } else {
                model.decreaseScore(BUTTONSCORE);
            }
            if (isCorrect) {
                model.increaseScore(BUTTONSCORE);
            }
            model.reset();
            assignColors();
            timer.playFromStart();
        } else {
            model.reset();
            assignColors();
            timer.playFromStart();
            isTimerExpired = false;
        }
    }

    public void assignColors() {
        for (int i = 0; i < 3; i++) {
            buttons.get(i).setText(ColorConverter.toString(model.getButton(i).get(0)));
            buttons.get(i).setTextFill(ColorConverter.convert(model.getButton(i).get(1)));
        }

        Model.Color mainTextColor;
        if (model.getButtonTypes().get(0)) {
            mainTextColor = model.getCurrentButton().get(0);
            lblButtonType.setText("Text");
        } else {
            mainTextColor = model.getCurrentButton().get(1);
            lblButtonType.setText("Color");
        }
        if (model.getButtonTypes().get(1)) {
            lblMain.setText(ColorConverter.toString(mainTextColor));
            lblMain.setTextFill(ColorConverter.convert(Model.Color.randomColor()));
            lblTextType.setText("Text");
        } else {
            lblMain.setTextFill(ColorConverter.convert(mainTextColor));
            lblMain.setText(ColorConverter.toString(Model.Color.randomColor()));
            lblTextType.setText("Color");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new Model();
        buttons = new ArrayList();
        types = new ArrayList();
        buttons.add(btn0);
        buttons.add(btn1);
        buttons.add(btn2);
        types.add(lblTextType);
        types.add(lblButtonType);

        lblScore.textProperty().bind(Bindings.format("Score: %1$d", model.score));
        lblTimer.textProperty().bind(Bindings.format("Time: %1$.2f", model.timeLeft));

        timer = new Timeline(
                new KeyFrame(new Duration(0.0), new KeyValue(model.timeLeft, STARTTIME)),
                new KeyFrame(new Duration(3000.0), new KeyValue(model.timeLeft, 0.0, Interpolator.LINEAR)));
        timer.setOnFinished(e -> {
            isTimerExpired = true;
            model.timerExpired(BUTTONSCORE);
        });
        isTimerExpired = false;
    }
}
