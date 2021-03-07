package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.animations.Shaker;

import java.io.IOException;

public class ClassFormController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldSection;

    @FXML
    private Button btnSubmit;

    @FXML
    private ImageView btnReturn;

    private final String ERROR_BORDER = "-fx-border-color: red;";

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

        // when add button is pressed
        btnSubmit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            // get values of text field
            String className = fieldName.getText();
            String sectionNumber = fieldSection.getText();

            // if text field is empty, give it error color
            if(className.equals("") || sectionNumber.equals("")) {

                if(className.equals("")) {
                    fieldName.setStyle(ERROR_BORDER);
                    fieldName.setPromptText("Enter a name for the class");
                }
                else {fieldName.setStyle("");}

                if(sectionNumber.equals("")) {
                    fieldSection.setStyle(ERROR_BORDER);
                    fieldSection.setPromptText("Enter section number (ex: 101)");
                }
                else {fieldSection.setStyle("");}

                // shake button
                Shaker submitShaker = new Shaker(btnSubmit);
                submitShaker.shake();
            }

            // if no text field is empty
            else {

                fieldName.setStyle("");
                fieldSection.setStyle("");

                // get fxml file as a url
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/classForm2.fxml"));

                try {

                    // load in a different fxml file into the window
                    AnchorPane formPane = loader.load();
                    anchorPane.getChildren().setAll(formPane);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // call a method in another controller
                ClassForm2Controller display = loader.getController();
                display.setClassInfo(className, sectionNumber);
            }
        });
    }

}