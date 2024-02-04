package model;

public class Model {

    private int cigaCounter = 1;



    public void setCigaCounter(int cigaCounter) {
        this.cigaCounter = cigaCounter;
    }

    public int getCigaCounter() {
        return cigaCounter;
    }


    public int cigaCounterIncr(){
        return cigaCounter++;
    }
}
