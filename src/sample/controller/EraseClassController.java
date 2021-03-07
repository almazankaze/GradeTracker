package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.database.DatabaseHandler;
import sample.model.Classes;
import sample.model.Grade;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EraseClassController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vBoxClasses;

    @FXML
    private ImageView btnReturn;

    private DatabaseHandler databaseHandler;
    private String classToDelete;

    private final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent;";
    private final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";

    @FXML
    void initialize() {

        // create new database
        databaseHandler = new DatabaseHandler();

        // fetch all info from database
        ResultSet userRow = databaseHandler.getClasses();

        // if database is not empty
        if(userRow != null) {
            try {

                // while there are rows in database
                while (userRow.next()) {

                    // get class name and section
                    String className = userRow.getString("classname");

                    // make a new button for each class
                    Button newButton = new Button(className);
                    newButton.setFont(Font.font("System", FontWeight.BOLD, 18));
                    newButton.setStyle(IDLE_BUTTON_STYLE);
                    newButton.setOnMouseEntered(e -> newButton.setStyle(HOVERED_BUTTON_STYLE));
                    newButton.setOnMouseExited(e -> newButton.setStyle(IDLE_BUTTON_STYLE));
                    newButton.setOnMouseClicked(e -> classButtonPressEvent(e));

                    // add the button to the window
                    vBoxClasses.getChildren().add(newButton);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // when return button is pressed
        btnReturn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            goToAddScreen();
        });
    }

    // method that handles what happens when a class button is pressed
    public void classButtonPressEvent(MouseEvent event) {

        // get value of button that was pressed
        Button button = (Button) event.getSource();
        classToDelete = button.getText();

        // create new database
        databaseHandler = new DatabaseHandler();

        Grade myGrade = new Grade();
        Classes myClass = new Classes();

        myClass.setClassName(classToDelete);

        // fetch all info from database
        ResultSet userRow = databaseHandler.getAClass(myClass);

        // if database is not empty
        if(userRow != null) {
            try {

                // while there are rows in database
                while (userRow.next()) {

                    // get class id
                    myGrade.setClassID(userRow.getString("classid"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            // delete the class
            databaseHandler.eraseAllGrades(myGrade);
            databaseHandler.eraseClass(myClass);
        }

        goToAddScreen();
    }

    // method to go back to add class screen
    public void goToAddScreen() {

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
