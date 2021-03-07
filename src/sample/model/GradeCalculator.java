package sample.model;

import java.util.ArrayList;

public class GradeCalculator {

    private Classes myClass;

    private float finalPercent;
    private float startingPercent;
    private String letterGrade;

    public GradeCalculator() {

        finalPercent = 0;
        startingPercent = 100;
        letterGrade = "A";
    }

    public GradeCalculator(Classes myClass) {

        this.myClass = myClass;

        finalPercent = 0;
        startingPercent = 100;
        letterGrade = "A";
    }

    public void calc(ArrayList<Float> hw, ArrayList<Float> test, ArrayList<Float> proj, ArrayList<Float> part,
                     ArrayList<Float> other1, ArrayList<Float> other2, ArrayList<Float> finalAssign) {

        float tmpPercent;
        float totalPercent = 0;
        float hwGrade = 0;
        float testGrade = 0;
        float projGrade = 0;
        float partGrade = 0;
        float finalGrade = 0;
        float other1Grade = 0;
        float other2Grade = 0;

        // calculate homework grade
        if(hw.size() > 0) {

            tmpPercent = myClass.getHomeWorkWeight()/hw.size();

            for(int i = 0; i < hw.size(); i++) {

                hwGrade = hwGrade + (hw.get(i) * tmpPercent);
            }
        }
        else {startingPercent = startingPercent - myClass.getHomeWorkWeight();}

        // calculate test grade
        if(test.size() > 0) {

            tmpPercent = myClass.getTestWeight()/test.size();

            for(int i = 0; i < test.size(); i++) {

                testGrade = testGrade + (test.get(i) * tmpPercent);
            }
        }
        else {startingPercent = startingPercent - myClass.getTestWeight();}

        // calculate project grade
        if(proj.size() > 0) {

            tmpPercent = myClass.getProjectWeight()/proj.size();

            for(int i = 0; i < proj.size(); i++) {

                projGrade = projGrade + (proj.get(i) * tmpPercent);
            }
        }
        else {startingPercent = startingPercent - myClass.getProjectWeight();}

        // calculate participation grade
        if(part.size() > 0) {

            tmpPercent = myClass.getParticipationWeight()/part.size();

            for(int i = 0; i < part.size(); i++) {

                partGrade = partGrade + (part.get(i) * tmpPercent);
            }
        }
        else {startingPercent = startingPercent - myClass.getParticipationWeight();}

        // calculate other 1 grade
        if(other1.size() > 0) {

            tmpPercent = myClass.getOther1Weight()/other1.size();

            for(int i = 0; i < other1.size(); i++) {

                other1Grade = other1Grade + (other1.get(i) * tmpPercent);
            }
        }
        else {startingPercent = startingPercent - myClass.getOther1Weight();}

        // calculate other 2 grade
        if(other2.size() > 0) {

            tmpPercent = myClass.getOther2Weight()/other2.size();

            for(int i = 0; i < other2.size(); i++) {

                other2Grade = other2Grade + (other2.get(i) * tmpPercent);
            }
        }
        else {startingPercent = startingPercent - myClass.getOther2Weight();}

        // calculate final exam grade
        if(finalAssign.size() > 0) {

            tmpPercent = myClass.getFinalWeight()/finalAssign.size();

            for(int i = 0; i < finalAssign.size(); i++) {

                finalGrade = finalGrade + (finalAssign.get(i) * tmpPercent);
            }
        }
        else {startingPercent = startingPercent - myClass.getFinalWeight();}

        totalPercent = totalPercent + hwGrade + testGrade + projGrade + partGrade + other1Grade + other2Grade + finalGrade;

        finalPercent = (totalPercent/startingPercent) * 100;

        // set grade letter
        if(finalPercent > 89) {
            letterGrade = "A";
        }
        else if(finalPercent > 79) {
            letterGrade = "B";
        }
        else if(finalPercent > 69) {
            letterGrade = "C";
        }
        else if(finalPercent > 59) {
            letterGrade = "D";
        }
        else {
            letterGrade = "F";
        }
    }

    public float getFinalPercent() {
        return finalPercent;
    }

    public String getLetterGrade() {
        return letterGrade;
    }
}
