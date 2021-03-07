package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.database.DatabaseHandler;
import sample.model.Assignment;
import sample.model.Classes;
import sample.model.Grade;
import sample.model.GradeCalculator;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GradeViewController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView btnReturn;

    @FXML
    private Label lblPercent;

    @FXML
    private Label lblLetter;

    @FXML
    private ImageView btnAddAssignment;

    @FXML
    private TableView<Assignment> table;

    @FXML
    private TableColumn<Assignment, String> myName;

    @FXML
    private TableColumn<Assignment, String> type;

    @FXML
    private TableColumn<Assignment, Float> myScore;

    @FXML
    private TableColumn<Assignment, Float> total;

    public ObservableList<Assignment> list = FXCollections.observableArrayList();

    private String classID;
    private Classes myClass;

    private DatabaseHandler databaseHandler;

    private ArrayList<Float> hwScores;
    private ArrayList<Float> testScores;
    private ArrayList<Float> projScores;
    private ArrayList<Float> partScores;
    private ArrayList<Float> other1Scores;
    private ArrayList<Float> other2Scores;
    private ArrayList<Float> finalScore;

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

        // when add button is pressed, go to add assignment screen
        btnAddAssignment.addEventHandler(MouseEvent.MOUSE_CLICKED, EVENT -> {

            // get fxml file as a url
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/addAssign.fxml"));

            try {

                // load in a different fxml file into the window
                AnchorPane formPane = loader.load();
                anchorPane.getChildren().setAll(formPane);

            } catch (IOException e) {
                e.printStackTrace();
            }

            // call a method in another controller
            AddAssignmentController display = loader.getController();
            display.setAssignInfo(classID, myClass);
        });
    }

    // set the class info from the button that was pressed in previous screen
    public void setClassGrade(String classID, Classes myClass) {

        this.classID = classID;
        this.myClass = myClass;

        // create new database
        databaseHandler = new DatabaseHandler();

        // create temp grade
        Grade myGrade = new Grade();
        myGrade.setClassID(classID);

        // fetch all info from database
        ResultSet userRow = databaseHandler.getMyGrades(myGrade);

        // if there is any data
        if(userRow != null) {

            hwScores = new ArrayList<>();
            testScores = new ArrayList<>();
            projScores = new ArrayList<>();
            partScores = new ArrayList<>();
            other1Scores = new ArrayList<>();
            other2Scores = new ArrayList<>();
            finalScore = new ArrayList<>();

            try {

                // while there are rows in database
                while (userRow.next()) {

                    // start fetching data and adding it to list
                    String n = userRow.getString("name");
                    String t = userRow.getString("type");
                    float s = userRow.getFloat("myscore");
                    float tot = userRow.getFloat("totalpoints");

                    Assignment assign = new Assignment(n, t, s, tot);
                    list.add(assign);

                    // calculate the assignment score
                    float assignScore = s/tot;

                    // add scores to arraylists
                    switch(t) {

                        case "Homework":
                            hwScores.add(assignScore);
                            break;
                        case "Test":
                            testScores.add(assignScore);
                            break;
                        case "Project":
                            projScores.add(assignScore);
                            break;
                        case "Participation":
                            partScores.add(assignScore);
                            break;
                        case "Other 1":
                            other1Scores.add(assignScore);
                            break;
                        case "Other 2":
                            other2Scores.add(assignScore);
                            break;

                            default:
                             finalScore.add(assignScore);
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // show the grades to the user
        myName.setCellValueFactory(new PropertyValueFactory<Assignment, String>("myName"));
        type.setCellValueFactory(new PropertyValueFactory<Assignment, String>("type"));
        myScore.setCellValueFactory(new PropertyValueFactory<Assignment, Float>("myScore"));
        total.setCellValueFactory(new PropertyValueFactory<Assignment, Float>("total"));
        table.setItems(list);

        // calculate overall grade
        GradeCalculator calculator = new GradeCalculator(myClass);

        calculator.calc(hwScores, testScores, projScores, partScores, other1Scores, other2Scores, finalScore);

        lblPercent.setText(String.valueOf(calculator.getFinalPercent()));
        lblLetter.setText(calculator.getLetterGrade());
    }
}