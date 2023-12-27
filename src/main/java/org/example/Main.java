package org.example;

import com.homework.manager.Scheduler;
import com.homework.node.Schedule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        BufferedReader reader;

        try {
            URL fileUrl = new Main().getClass().getResource("/sample.csv");
            reader = new BufferedReader(new FileReader(fileUrl.getFile()));
            String line = reader.readLine();
            int lineCount = 0;
            Scheduler scheduler = Scheduler.getInstance();

            while (line != null) {
                try{
                    List<Integer> list = Arrays.asList(line.split(",")).stream().map(Integer::new).collect(Collectors.toList());
                    int lowerValue = list.get(0);
                    int upperValue = list.get(1);

                    if(lowerValue == 0){
                        Schedule found = scheduler.find(upperValue);
                        if(found == null){
                            System.out.println(line + "-> line not found");
                        }
                        else{
                            scheduler.deleteSchedule(found.getLowerValue(), found.getUpperValue());
                            System.out.println(line + "-> line deleted");
                        }
                    }
                    else{
                        if(scheduler.isOverlappingSchedule(lowerValue, upperValue)){
                            System.out.println(line + "-> false");
                        }
                        else{
                            scheduler.addSchedule(lowerValue, upperValue, lineCount);
                            System.out.println(line + "-> true");
                        }
                    }
                }
                catch(Exception ex){
                    System.out.println(line + "-> false (lower limit shouldn't be greater than the upper limit [LOWER][UPPER])");
                }
                // read next line
                line = reader.readLine();
                lineCount++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}