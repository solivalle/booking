package com.homework.node;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScheduleTest {

    @Test
    public void incorrectLimits(){
        assertThrows(RuntimeException.class, () -> new Schedule(10,5,1));
    }

    @Test
    public void setIsBeforeWithIncorrectLimits(){
        assertThrows(RuntimeException.class, () -> new Schedule(10,20,1).isBefore(20,10));
    }

    @Test
    public void setIsAfterWithIncorrectLimits(){
        assertThrows(RuntimeException.class, () -> new Schedule(10,20,1).isAfter(20,10));
    }

    @Test
    public void setOverrideWithIncorrectLimits(){
        assertThrows(RuntimeException.class, () -> new Schedule(10,20,1).override(20,10));
    }

}
