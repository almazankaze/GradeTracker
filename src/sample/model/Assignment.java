package sample.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.text.Font;

public class Assignment {

    private final SimpleStringProperty myName;
    private final SimpleStringProperty type;
    private final SimpleFloatProperty myScore;
    private final SimpleFloatProperty total;

    public Assignment(String myName, String type, Float myScore, Float total) {

        super();
        this.myName = new SimpleStringProperty(myName);
        this.type = new SimpleStringProperty(type);
        this.myScore = new SimpleFloatProperty(myScore);
        this.total = new SimpleFloatProperty(total);
    }

    public String getMyName() {
        return myName.get();
    }

    public String getType() {
        return type.get();
    }

    public float getMyScore() {
        return myScore.get();
    }

    public float getTotal() {
        return total.get();
    }
}
