package com.homework.node;

public class Schedule {

    private int lowerValue;
    private int upperValue;
    private int line;
    private Schedule before;
    private Schedule after;

    public Schedule(int lowerValue, int upperValue, int line){
        validateRange(lowerValue, upperValue);
        this.lowerValue = lowerValue;
        this.upperValue = upperValue;
        this.line = line;
    }

    public Schedule getBefore(){
        return this.before;
    }

    public Schedule getAfter(){
        return this.after;
    }

    public int getLine(){
        return this.line;
    }

    public void setBefore(Schedule before){
        this.before = before;
    }

    public void setAfter(Schedule after){
        this.after = after;
    }

    public void override(int lowerValue, int upperValue){
        validateRange(lowerValue, upperValue);
        this.lowerValue = lowerValue;
        this.upperValue = upperValue;
    }

    public boolean isBefore(int lowerValue, int upperValue){
        validateRange(lowerValue, upperValue);
        return this.lowerValue >= upperValue;
    }

    public boolean isAfter(int lowerValue, int upperValue){
        validateRange(lowerValue, upperValue);
        return this.upperValue <= lowerValue;
    }

    public int getLowerValue(){
        return this.lowerValue;
    }

    public int getUpperValue(){
        return this.upperValue;
    }

    public boolean isEquals(int lowerValue, int upperValue){
        return this.lowerValue == lowerValue && this.upperValue == upperValue;
    }

    private void validateRange(int lowerValue, int upperValue){
        if(upperValue < lowerValue)
            throw new RuntimeException("Schedule not supported");
    }


}