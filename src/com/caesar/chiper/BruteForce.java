package com.caesar.chiper;

import java.io.*;

/*Если условиться, что русский алфавит состоит из 33 букв и пробела, то окажется, что самый частый символ — это пробел (14,46%),
дальше следуют гласные О (9,42%), Е (7,33%), И (6,72%), А (6,52%) и согласные Н (5,83%), Т (5,56%).
А реже всего встречаются буквы Ф (0,27%), Ъ (0,03%) и Ё (0,01%).
Конечно, в каждом конкретном тексте частоты могут отличаться от приведённых, но эти отклонения будут несущественными.
https://elementy.ru/nauchno-populyarnaya_biblioteka/436049/Statistika_yazyka
*/

public class BruteForce {

    public static String search(String fileName) throws IOException {
        int key = 0;
        int countSpaces = 0;
        String text = "";
        String resultText = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                text += line + "\n";
            }
        }

        for (int i = 1; i < CaesarsChiper.ALPHABET.length()-1; i++) {
            CaesarsChiper chiper = new CaesarsChiper(i);
            String result = chiper.decodeText(text);
            if (result.split(" ").length - 1 > countSpaces) {
                countSpaces = result.split(" ").length - 1;
                key = i;
                resultText = result;

                //System.out.println("key = " + i);
                //System.out.println(result);
            }

        }

        return resultText;
    }

}