package com.caesar.chiper;

import java.io.*;
import java.util.ArrayList;

interface FileOperation {
    String apply(String filename) throws IOException;
}

public class CaesarsChiper {

    public static final String ALPHABET = "«»:!? АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static ArrayList<Character> alphabetList = new ArrayList<>(ALPHABET.length());
    private int key;

    static {
        for (int i = 0; i < ALPHABET.length(); i++) {
            alphabetList.add(ALPHABET.charAt(i));
        }
    }

    public CaesarsChiper(int key) {
        this.key = key;
    }

    private char encodeChar(char symbol) {
        int position = alphabetList.indexOf((char)symbol);
        if (position == -1) {
            return symbol;
        } else {
            position = (position + key) % ALPHABET.length();
            return alphabetList.get(position);
        }
    }

    private char decodeChar(char symbol) {
        int position = alphabetList.indexOf((char)symbol);
        if (position == -1) {
            return symbol;
        } else {
            position = (position - key + ALPHABET.length()) % ALPHABET.length();
            return alphabetList.get(position);
        }
    }

    public String encodeText(String inputString) {
        char[] inputArray = inputString.toCharArray();
        char[] outputArray = new char[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
            outputArray[i] = encodeChar(inputArray[i]);
        }

        return new String(outputArray);
    }

    public String decodeText(String inputString) {
        char[] inputArray = inputString.toCharArray();
        char[] outputArray = new char[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
            outputArray[i] = decodeChar(inputArray[i]);
        }

        return new String(outputArray);
    }

    public void encodeFile(String src, String dist) throws IOException {
        copyFile(src,dist,(var str)->{ return this.encodeText(str);});
    }

    public void decodeFile(String src, String dist) throws IOException {
        copyFile(src,dist,(var str)->{ return this.decodeText(str);});
    }

    private void copyFile(String src, String dist, FileOperation operation) throws IOException,FileNotFoundException {
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