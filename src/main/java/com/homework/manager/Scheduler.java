package com.homework.manager;

import com.homework.node.Schedule;

public class Scheduler {

    private Schedule root;
    private static Scheduler instance;

    private Scheduler(){
    }

    public static synchronized Scheduler getInstance(){
        if(instance == null){
            instance = new Scheduler();
        }

        return instance;
    }

    public void addSchedule(int lowerValue, int upperValue, int line) {
        root = addSchedule(root, lowerValue, upperValue, line);
    }

    public boolean isOverlappingSchedule(int lowerValue, int upperValue) {
        return isOverlappingSchedule(root,lowerValue,upperValue);
    }

    public void deleteSchedule(int lowerValue, int upperValue) {
        root = deleteSchedule(root, lowerValue, upperValue);
    }

    private Schedule addSchedule(Schedule schedule, int lowerValue, int upperValue, int line) {
        if (schedule == null) {
            return new Schedule(lowerValue, upperValue, line);
        }

        if (schedule.isBefore(lowerValue, upperValue)) {
            schedule.setBefore(addSchedule(schedule.getBefore(), lowerValue, upperValue, line));
        }
        else if (schedule.isAfter(lowerValue, upperValue)) {
            schedule.setAfter(addSchedule(schedule.getAfter(), lowerValue, upperValue, line));
        }

        return schedule;
    }

    private boolean isOverlappingSchedule(Schedule schedule, int lowerValue, int upperValue) {
        if (schedule == null) {
            return false;
        }

        if (!schedule.isAfter(lowerValue, upperValue) && !schedule.isBefore(lowerValue, upperValue)) {
            return true;
        }

        if(schedule.isBefore(lowerValue, upperValue))
            return isOverlappingSchedule(schedule.getBefore(), lowerValue, upperValue);

        return isOverlappingSchedule(schedule.getAfter(), lowerValue, upperValue);
    }

    private Schedule deleteSchedule(Schedule schedule, int lowerValue, int upperValue) {
        if (schedule == null) {
            return null;
        }

        if (schedule.isEquals(lowerValue, upperValue)) {
            //no child
            if (schedule.getBefore() == null && schedule.getAfter() == null) {
                return null;
            }
            //1 child left
            if (schedule.getAfter() == null) {
                return schedule.getBefore();
            }
            //1 child left
            if (schedule.getBefore() == null) {
                return schedule.getAfter();
            }
            //2 children
            Schedule newCurrent = findSmallestValue(schedule.getAfter());
            schedule.override(newCurrent.getLowerValue(), newCurrent.getUpperValue());
            schedule.setAfter(deleteSchedule(schedule.getAfter(), newCurrent.getLowerValue(), newCurrent.getUpperValue()));
            return schedule;

        }
        if (schedule.isBefore(lowerValue, upperValue)) {
            schedule.setBefore(deleteSchedule(schedule.getBefore(), lowerValue, upperValue));
            return schedule;
        }

        schedule.setAfter(deleteSchedule(schedule.getAfter(), lowerValue, upperValue));
        return schedule;
    }

    private Schedule findSmallestValue(Schedule schedule) {
        return schedule.getBefore() == null ? schedule : findSmallestValue(schedule.getBefore());
    }

    public Schedule find(int line){
        return find(root, line);
    }

    private Schedule find(Schedule schedule, int line) {

        if(schedule == null){
            return null;
        }

        if(schedule.getLine() == line){
            return schedule;
        }

        Schedule beforeResult = null;
        Schedule afterResult = null;

        if(schedule.getBefore() != null){
            beforeResult = find(schedule.getBefore(), line);
        }

        if(schedule.getAfter() != null){
            afterResult = find(schedule.getAfter(), line);
        }

        if(beforeResult != null)
            return beforeResult;

        return afterResult;

    }

    //for testing
    public Schedule getRoot(){
        return root;
    }

}
