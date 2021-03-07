package sample.database;

public class Const {

    public static final String CLASSES_TABLE = "classes";
    public static final String GRADES_TABLE = "grades";

    //Class Table Column Names
    public static final String CLASSES_ID = "classid";
    public static final String CLASSES_CLASSNAME = "classname";
    public static final String CLASSES_HOMEWORKWEIGHT = "homework";
    public static final String CLASSES_TESTWEIGHT = "test";
    public static final String CLASSES_PROJECTWEIGHT = "project";
    public static final String CLASSES_PARTICIPATIONWEIGHT = "participation";
    public static final String CLASSES_FINALWEIGHT = "final";
    public static final String CLASSES_OTHER1WEIGHT = "other1";
    public static final String CLASSES_OTHER2WEIGHT = "other2";

    //Grades Table Column Names
    public static final String GRADES_ID = "idgrades";
    public static final String GRADES_CLASS_ID = "classid";
    public static final String GRADES_NAME = "name";
    public static final String GRADES_TYPE = "type";
    public static final String GRADES_MYSCORE = "myscore";
    public static final String GRADES_TOTAL = "totalpoints";

}
