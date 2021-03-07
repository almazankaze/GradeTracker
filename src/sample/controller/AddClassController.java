package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.animations.Shaker;
import sample.database.DatabaseHandler;
import sample.model.Classes;

import java.sql.SQLException;

public class AddClassController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vBoxClasses;

    @FXML
    private ImageView btnErase;

    private final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent;";
    private final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";

    private DatabaseHandler databaseHandler;
    private String className;
    private int numButtons = 0;
    private Classes myClass;

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
                    className = userRow.getString("classname");

                    // make a new button for each class
                    Button newButton = new Button(className);
                    newButton.setFont(Font.font("System", FontWeight.BOLD, 18));
                    newButton.setStyle(IDLE_BUTTON_STYLE);
                    newButton.setOnMouseEntered(e -> newButton.setStyle(HOVERED_BUTTON_STYLE));
                    newButton.setOnMouseExited(e -> newButton.setStyle(IDLE_BUTTON_STYLE));
                    newButton.setOnMouseClicked(e -> classButtonPressEvent(e));

                    numButtons += 1;

                    // add the button to the window
                    vBoxClasses.getChildren().add(newButton);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // create the add button
        File file = new File("src/sample/assets/baseline_add_circle_white_48dp.png");
        Image image = new Image(file.toURI().toString());
        ImageView addButton = new ImageView();
        addButton.setImage(image);

        // add the button to the window
        vBoxClasses.getChildren().add(addButton);

        // when add button is pressed
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            try {

                // load in a different fxml file into the window
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/classForm.fxml"));
                anchorPane.getChildren().setAll(formPane);

            } catch(IOException e) {
                e.printStackTrace();
            }

        });

        // go to erase class window when clicked
        btnErase.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            // if there are classes, go to erase page
            if(numButtons > 0) {
                try {

                    // load in a different fxml file into the window
                    AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/eraseClass.fxml"));
                    anchorPane.getChildren().setAll(formPane);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // else shake button error
            else {

                // shake erase button
                Shaker submitShaker = new Shaker(btnErase);
                submitShaker.shake();
            }
        });
    }

    // method that handles what happens when a class button is pressed
    public void classButtonPressEvent(MouseEvent event) {

        // get value of button that was pressed
        Button button = (Button) event.getSource();
        className = button.getText();

        int classID = 0;

        // create temp database
        databaseHandler = new DatabaseHandler();

        // create temp class
        myClass = new Classes();
        myClass.setClassName(className);

        // fetch info from class with certain name in database
        ResultSet userRow = databaseHandler.getAClass(myClass);

        try {

            while(userRow.next()) {
                classID = userRow.getInt("classid");
                myClass.setHomeWorkWeight(userRow.getFloat("homework"));
                myClass.setTestWeight(userRow.getFloat("test"));
                myClass.setProjectWeight(userRow.getFloat("project"));
                myClass.setParticipationWeight(userRow.getFloat("participation"));
                myClass.setFinalWeight(userRow.getFloat("final"));
                myClass.setOther1Weight(userRow.getFloat("other1"));
                myClass.setOther2Weight(userRow.getFloat("other2"));

                /*
                projWeight = userRow.getFloat("project");
                partWeight = userRow.getFloat("participation");
                finalWeight = userRow.getFloat("final");
                other1Weight = userRow.getFloat("other1");
                other2Weight = userRow.getFloat("other2");
                 */
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        // show user the grade
        showGradeScreen(classID);
    }

    // method that shows the grade screen
    public void showGradeScreen(int id) {

        // get fxml file as a url
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/gradeView.fxml"));

        String classID = String.valueOf(id);

        // go to grade view
        try {

            // load in a different fxml file into the window
            AnchorPane formPane = loader.load();
            anchorPane.getChildren().setAll(formPane);

        } catch(IOException e) {
            e.printStackTrace();
        }

        // call a method in another controller
        GradeViewController display = loader.getController();
        display.setClassGrade(classID, myClass);
    }

}
