package sample.model;

public class Grade {

    private String classID;
    private String name;
    private String type;
    private float myScore;
    private float total;

    public Grade() {

    }

    public Grade(String classID, String name, String type, float myScore, float total) {
        this.classID = classID;
        this.name = name;
        this.type = type;
        this.myScore = myScore;
        this.total = total;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getMyScore() {
        return myScore;
    }

    public void setMyScore(float myScore) {
        this.myScore = myScore;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
