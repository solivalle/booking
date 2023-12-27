package org.example;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void testMain(){
        String filePath = "/sample.csv";
        String[] args = new String[1];
        args[0] = filePath;
        Main.main(args);

    }

}
