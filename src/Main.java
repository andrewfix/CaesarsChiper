import com.caesar.chiper.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static String text = "";
    public static String result = "";
    public static int key;
    public static CaesarsChiper chiper;

    public static void main(String[] args) throws IOException {
        showMenu();
        while (scanner.hasNext()) {
            String choice = scanner.nextLine();
            switch (choice) {
                case "1", "2", "3", "4" -> {
                    enterKey();
                    chiper = new CaesarsChiper(key);
                }
                case "5" -> {

                }
                case "6" -> {
                    System.out.println("Bye!");
                    System.exit(0);
                }
                default -> {
                    continue;
                }
            }

            try {

                switch (choice) {
                    case "1" -> {
                        System.out.println("Введите строку для кодирования: ");
                        result = chiper.encodeText(scanner.next());
                        System.out.println("Результат кодирования:");
                        System.out.println(result);
                    }
                    case "2" -> {
                        System.out.println("Введите строку для декодирования: ");
                        result = chiper.decodeText(scanner.next());
                        System.out.println("Результат декодирования:");
                        System.out.println(result);
                    }
                    case "3", "4" -> {
                        String srcFile, distFile;
                        System.out.println("Введите имя файла источника: ");
                        srcFile = scanner.next();
                        System.out.println("Введите имя файла для вывода результата: ");
                        distFile = scanner.next();

                        if (choice.equals("3")) {
                            chiper.encodeFile(srcFile, distFile);
                        } else {
                            chiper.decodeFile(srcFile, distFile);
                        }

                    }
                    case "5" -> {
                        System.out.println("Введите имя файла источника: ");
                        String result = BruteForce.search(scanner.next());
                        System.out.println(result);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + choice);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                showMenu();
            }
        }
    }

    private static void showMenu() {
        System.out.println("1. Encode text");
        System.out.println("2. Decode text");
        System.out.println("3. Encode file");
        System.out.println("4. Decode file");
        System.out.println("5. BruteForce");
        System.out.println("6. Exit");
    }

    private static void enterKey() {
        System.out.println("Please enter the key (offset): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка! Введите целое число:");
            scanner.next();
        }
        key = scanner.nextInt();
    }

}
