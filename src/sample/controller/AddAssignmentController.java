package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.animations.Shaker;
import sample.database.DatabaseHandler;
import sample.model.Classes;
import sample.model.Grade;

import java.io.IOException;

public class AddAssignmentController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView btnReturn;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldScore;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField fieldTotal;

    @FXML
    private ToggleGroup type;

    @FXML
    private RadioButton radioHW;

    @FXML
    private RadioButton radioT;

    @FXML
    private RadioButton radioProj;

    @FXML
    private RadioButton radioPart;

    @FXML
    private RadioButton radioF;

    @FXML
    private RadioButton radioO1;

    @FXML
    private RadioButton radioO2;

    private final String ERROR_BORDER = "-fx-border-color: red;";

    private String classID;
    private Classes myClass;

    @FXML
    void initialize() {

        // when return button is pressed
        btnReturn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            // get fxml file as a url
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/gradeView.fxml"));

            try {

                // load in a different fxml file into the window
                AnchorPane formPane = loader.load();
                anchorPane.getChildren().setAll(formPane);

            } catch (IOException e) {
                e.printStackTrace();
            }

            // call a method in another controller
            GradeViewController display = loader.getController();
            display.setClassGrade(classID, myClass);
        });

        // when submit button is clicked
        btnSubmit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            // if name field or score field is empty, show error
            if(fieldName.getText().equals("") || fieldTotal.getText().equals("")) {

                if(fieldName.getText().equals("")) {
                    fieldName.setStyle(ERROR_BORDER);
                    fieldName.setPromptText("Enter a name for this assignment");
                }
                else {fieldName.setStyle("");}

                if(fieldTotal.getText().equals("")) {
                    fieldTotal.setStyle(ERROR_BORDER);
                }
                else {fieldTotal.setStyle("");}

                // shake button
                Shaker submitShaker = new Shaker(btnSubmit);
                submitShaker.shake();
            }
            else {
                createGrade();
            }
        });
    }

    // create the assignment with  grade
    public void createGrade() {

        String name = fieldName.getText();
        float myScore = Float.parseFloat(0 + fieldScore.getText());
        float total = Float.parseFloat(0 + fieldTotal.getText());

        RadioButton selectedRadio = (RadioButton) type.getSelectedToggle();
        String typeVal = selectedRadio.getText();

        // create database
        DatabaseHandler databaseHandler = new DatabaseHandler();

        // create a new assignment
        Grade grade = new Grade(classID, name, typeVal, myScore, total);

        // add the grade to the grades database
        databaseHandler.addGrade(grade);

        // get fxml file as a url
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/gradeView.fxml"));

        try {

            // load in a different fxml file into the window
            AnchorPane formPane = loader.load();
            anchorPane.getChildren().setAll(formPane);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // call a method in another controller
        GradeViewController display = loader.getController();
        display.setClassGrade(classID, myClass);
    }

    // set some assignment info
    public void setAssignInfo(String classID, Classes myClass) {

        this.classID = classID;
        this.myClass = myClass;
    }
}

