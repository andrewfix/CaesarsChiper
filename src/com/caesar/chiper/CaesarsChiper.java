package com.caesar.chiper;

import java.io.*;
import java.util.ArrayList;


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
        int position = alphabetList.indexOf((char) symbol);
        if (position == -1) {
            return symbol;
        } else {
            position = (position + key) % ALPHABET.length();
            return alphabetList.get(position);
        }
    }

    private char decodeChar(char symbol) {
        int position = alphabetList.indexOf((char) symbol);
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
        Utils.copyFile(src, dist, (var str) -> {
            return this.encodeText(str);
        });
    }

    public void decodeFile(String src, String dist) throws IOException {
        Utils.copyFile(src, dist, (var str) -> {
            return this.decodeText(str);
        });
    }
}