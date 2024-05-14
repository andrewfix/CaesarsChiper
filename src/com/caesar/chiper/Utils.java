package com.caesar.chiper;

import java.io.*;
import java.util.function.Function;

public class Utils {

    public static void copyFile(String src, String dist, Function<String, String> operation) throws IOException {
        File outputFile = new File(dist);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        try (FileReader freader = new FileReader(src);
             BufferedReader br = new BufferedReader(freader);

             FileWriter fwriter = new FileWriter(outputFile);
             BufferedWriter bw = new BufferedWriter(fwriter)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                String resultLine = operation.apply(line);
                bw.write(resultLine);
                bw.newLine();
            }
        }
    }

}