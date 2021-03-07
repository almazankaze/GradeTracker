package sample.database;

import sample.model.Classes;
import sample.model.Grade;

import java.io.IOException;
import java.lang.constant.Constable;
import java.sql.*;

public class DatabaseHandler extends Configs {

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/"
                + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    // write to classes database
    public void addClass(Classes myClass) {

        String insert = "INSERT INTO " + Const.CLASSES_TABLE + "(" + Const.CLASSES_CLASSNAME
                + ","  + Const.CLASSES_HOMEWORKWEIGHT + "," + Const.CLASSES_TESTWEIGHT + "," + Const.CLASSES_PROJECTWEIGHT + ","
                + Const.CLASSES_PARTICIPATIONWEIGHT + "," + Const.CLASSES_FINALWEIGHT + "," + Const.CLASSES_OTHER1WEIGHT + "," + Const.CLASSES_OTHER2WEIGHT + ")" + "VALUES(?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, myClass.getClassName());
            preparedStatement.setFloat(2, myClass.getHomeWorkWeight());
            preparedStatement.setFloat(3, myClass.getTestWeight());
            preparedStatement.setFloat(4, myClass.getProjectWeight());
            preparedStatement.setFloat(5, myClass.getParticipationWeight());
            preparedStatement.setFloat(6, myClass.getFinalWeight());
            preparedStatement.setFloat(7, myClass.getOther1Weight());
            preparedStatement.setFloat(8, myClass.getOther2Weight());

            preparedStatement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // returns all classes
    public ResultSet getClasses() {

        ResultSet resultSet = null;

        String query = "SELECT * FROM " + Const.CLASSES_TABLE;

        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

        } catch(SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    // returns a class by searching by name
    public ResultSet getAClass(Classes myClass) {

        ResultSet resultSet = null;

        if(!myClass.getClassName().equals("")) {

            String query = "SELECT * FROM " + Const.CLASSES_TABLE + " WHERE "
                    + Const.CLASSES_CLASSNAME + "=?";

            // select all from users that match class name
            try {

                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, myClass.getClassName());

                resultSet = preparedStatement.executeQuery();

            } catch(SQLException e) {
                e.printStackTrace();
            } catch(ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        return resultSet;
    }

    // this method will erase a class
    public void eraseClass(Classes myClass) {

        String remove = "DELETE FROM " + Const.CLASSES_TABLE + " WHERE " + Const.CLASSES_CLASSNAME + "=?";

        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(remove);

            preparedStatement.setString(1, myClass.getClassName());

            preparedStatement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // write to grades database
    public void addGrade(Grade grade) {

        String insert = "INSERT INTO " + Const.GRADES_TABLE + "(" + Const.GRADES_CLASS_ID
                + ","  + Const.GRADES_NAME + "," + Const.GRADES_TYPE + "," + Const.GRADES_MYSCORE + ","
                + Const.GRADES_TOTAL +  ")" + "VALUES(?,?,?,?,?)";

        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, grade.getClassID());
            preparedStatement.setString(2, grade.getName());
            preparedStatement.setString(3, grade.getType());
            preparedStatement.setFloat(4, grade.getMyScore());
            preparedStatement.setFloat(5, grade.getTotal());

            preparedStatement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // returns all grades associated with a class
    public ResultSet getMyGrades(Grade grade) {

        ResultSet resultSet = null;

        String query = "SELECT * FROM " + Const.GRADES_TABLE + " WHERE "
                + Const.GRADES_CLASS_ID + "=?";

        // select all from grades that match class id
        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, grade.getClassID());

            resultSet = preparedStatement.executeQuery();

        } catch(SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    // this method will erase all grades from a class
    public void eraseAllGrades(Grade grade) {

        String remove = "DELETE FROM " + Const.GRADES_TABLE + " WHERE " + Const.GRADES_CLASS_ID + "=?";

        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(remove);

            preparedStatement.setString(1, grade.getClassID());

            preparedStatement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
