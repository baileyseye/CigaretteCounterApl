package model;

public class Model {

    private int cigaretteCounter = 0;


    public void setCigaretteCounter(int cigaretteCounter) {
        this.cigaretteCounter = cigaretteCounter;
    }

    public int getCigaretteCounter() {
        return cigaretteCounter;
    }


    public int cigaCounterIncr() {
        return cigaretteCounter++;
    }
}
