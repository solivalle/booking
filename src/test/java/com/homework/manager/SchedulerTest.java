package com.homework.manager;

import com.homework.node.Schedule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SchedulerTest {

    @Test
    public void initializeCorrectly(){
        Scheduler scheduler = Scheduler.getInstance();
        Schedule schedule = scheduler.find(1);
        assert(schedule == null);
    }

    @Test
    public void add3NodesNoOrder(){
        Scheduler scheduler = Scheduler.getInstance();
        scheduler.addSchedule(10,20,0);
        scheduler.addSchedule(20,30, 1);
        scheduler.addSchedule(30,40,2);

        assert(scheduler.getRoot().getLine() == 0);
    }

    @Test
    public void add3NodesWithIncorrectRanges(){
        Scheduler scheduler = Scheduler.getInstance();
        scheduler.addSchedule(10,20,0);
        assertThrows(RuntimeException.class, () -> scheduler.addSchedule(40,30, 1));
        assertThrows(RuntimeException.class, () -> scheduler.addSchedule(50,40,2));

        assert(scheduler.getRoot().getLine() == 0);
    }

    @Test
    public void add3NodesThenDelete(){
        Scheduler scheduler = Scheduler.getInstance();
        scheduler.addSchedule(10,20,0);
        scheduler.addSchedule(20,30, 1);
        scheduler.addSchedule(30,40,2);

        //delete root
        Schedule schedule = scheduler.find(0);
        assert(schedule != null);
        scheduler.deleteSchedule(schedule.getLowerValue(), schedule.getUpperValue());
        assert(scheduler.getRoot().getLine() == 1);
        schedule = scheduler.find(1);
        assert(schedule != null);
        scheduler.deleteSchedule(schedule.getLowerValue(), schedule.getUpperValue());
        assert(scheduler.getRoot().getLine() == 2);
        schedule = scheduler.find(2);
        assert(schedule != null);
        scheduler.deleteSchedule(schedule.getLowerValue(), schedule.getUpperValue());
        assert(scheduler.getRoot() == null);
    }

}
