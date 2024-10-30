//При старте, приложение предлагает начать новую игру или выйти из приложения
//При начале новой игры, случайным образом загадывается слово, и игрок начинает процесс по его отгадыванию
//После каждой введенной буквы выводим в консоль счётчик ошибок, текущее состояние виселицы (нарисованное ASCII символами)
//По завершении игры выводим результат (победа или поражение) и возвращаемся к состоянию #1 - предложение начать новую игру или выйти из приложения

//System.out.println("=====================");
//System.out.println("1. Новая игра");
//System.out.println("2. Выход");
//System.out.println("=====================");
//System.out.print("Выберите опцию: ");

//System.out.println("Слово: " + );
//System.out.println("Ошибки: " + error);
//System.out.println("Введите букву: ");

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Path FILE_PATH = Paths.get("words.txt");
    private static ArrayList<String> listWords = new ArrayList<>();
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Проверяем реализованный метод
        listWords = getListWords();
        do {
            drawingStartMenu();

            int inputMenu = scanner.nextInt();
            switch (inputMenu) {
                case 1:
                    startGameLoop();
                    break;
                case 2:
                    System.out.println("Всего доброго!");
                    return;
                default:
                    System.out.println("Некорректный ввод. Повторите попытку!");
            }
        } while (true);
    }

    public static void drawingStartMenu() {
        System.out.println("=====================");
        System.out.println("1. Новая игра");
        System.out.println("2. Выход");
        System.out.println("=====================");
        System.out.print("Выберите опцию: ");
    }

    // Основной цикл игры
    public static void startGameLoop() {
        String word = getRandomWord();
        char[] hiddenWord = new char[word.length()];
        Arrays.fill(hiddenWord, '*');
        System.out.println(hiddenWord);

        while (new String(hiddenWord).contains("*")) {
            // Остановился здесь
        }
    }

    // Метод для чтения слов из файла и загрузка их в коллекцию
    public static ArrayList<String> getListWords() {
        // Читаем файл построчно и добавляем в коллецию
        // Должны вернуть ArrayList со словами
        try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                listWords.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return listWords;
    }

    // Метод для получения рандомного слова из коллекции
    public static String getRandomWord() {
        return listWords.get(random.nextInt(listWords.size()));
    }

    public static char[] getHiddenWorld(String word) {
        char[] hiddenWord = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            // Подумать и не запутаться
        }
        return hiddenWord;
    }
}