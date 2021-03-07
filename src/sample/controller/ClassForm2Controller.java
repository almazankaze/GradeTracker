package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.animations.Shaker;
import sample.database.DatabaseHandler;

import sample.model.Classes;

import java.io.IOException;

public class ClassForm2Controller {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView btnReturn;

    @FXML
    private TextField fieldHW;

    @FXML
    private TextField fieldTest;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField fieldProj;

    @FXML
    private TextField fieldPartic;

    @FXML
    private TextField fieldOther1;

    @FXML
    private TextField fieldOther2;

    @FXML
    private TextField fieldFinal;

    @FXML
    private Label lblError;

    private String className;

    @FXML
    void initialize() {

        // when return button is pressed
        btnReturn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            // get fxml file as a url
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/addClass.fxml"));

            try {

                // load in a different fxml file into the window
                AnchorPane formPane = loader.load();
                anchorPane.getChildren().setAll(formPane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnSubmit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            createClass();
        });
    }

    // create a class
    public void createClass() {

        // assign variables
        float homeWork = Integer.parseInt(0 + fieldHW.getText());
        float test = Integer.parseInt(0 +fieldTest.getText());
        float project = Integer.parseInt(0 + fieldProj.getText());
        float part = Integer.parseInt(0 +fieldPartic.getText());
        float finalW = Integer.parseInt(0 +fieldFinal.getText());
        float other1 = Integer.parseInt(0 + fieldOther1.getText());
        float other2 = Integer.parseInt(0 + fieldOther2.getText());

        float total = homeWork + test + project + part + finalW + other1 + other2;

        // check if percentage is equal to 100
        if(total != 100) {

            // show error message
            lblError.setVisible(true);

            // shake button
            Shaker submitShaker = new Shaker(btnSubmit);
            submitShaker.shake();
        }
        else {

            lblError.setVisible(false);

            // create database
            DatabaseHandler databaseHandler = new DatabaseHandler();

            // create class
            Classes myClass = new Classes(className, homeWork, test, project, part, finalW, other1, other2);

            // add class to database
            databaseHandler.addClass(myClass);

            // get fxml file as a url
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/addClass.fxml"));

            try {

                // load in a different fxml file into the window
                AnchorPane formPane = loader.load();
                anchorPane.getChildren().setAll(formPane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // set initial class info
    public void setClassInfo(String className, String sectionNumber) {

        this.className = className + " " + sectionNumber;
    }

}