package sample.model;

public class  Classes {

    private String className;
    private float homeWorkWeight;
    private float  testWeight;
    private float projectWeight;
    private float participationWeight;
    private float finalWeight;
    private float other1Weight;
    private float other2Weight;

    public Classes() {

    }

    public Classes(String className, float homeWorkWeight, float testWeight, float projectWeight, float participationWeight, float finalWeight, float other1Weight, float other2Weight) {
        this.className = className;
        this.homeWorkWeight = homeWorkWeight;
        this.testWeight = testWeight;
        this.projectWeight = projectWeight;
        this.participationWeight = participationWeight;
        this.finalWeight = finalWeight;
        this.other1Weight = other1Weight;
        this.other2Weight = other2Weight;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public float getHomeWorkWeight() {
        return homeWorkWeight;
    }

    public void setHomeWorkWeight(float homeWorkWeight) {
        this.homeWorkWeight = homeWorkWeight;
    }

    public float getTestWeight() {
        return testWeight;
    }

    public void setTestWeight(float testWeight) {
        this.testWeight = testWeight;
    }

    public float getProjectWeight() {
        return projectWeight;
    }

    public void setProjectWeight(float projectWeight) {
        this.projectWeight = projectWeight;
    }

    public float getParticipationWeight() {
        return participationWeight;
    }

    public void setParticipationWeight(float participationWeight) {
        this.participationWeight = participationWeight;
    }

    public float getFinalWeight() {
        return finalWeight;
    }

    public void setFinalWeight(float finalWeight) {
        this.finalWeight = finalWeight;
    }

    public float getOther1Weight() {
        return other1Weight;
    }

    public void setOther1Weight(float other1Weight) {
        this.other1Weight = other1Weight;
    }

    public float getOther2Weight() {
        return other2Weight;
    }

    public void setOther2Weight(float other2Weight) {
        this.other2Weight = other2Weight;
    }
}
