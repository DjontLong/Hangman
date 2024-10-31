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
    private static int maxAttempts = 9;

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
        System.out.println("Слов загружено: " + listWords.size());
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

        // Счетчик ошибок
        int error = 0;

        while (new String(hiddenWord).contains("*") && maxAttempts > 0) {
            // Вызываем метод состояния игры
            showGameState(error, hiddenWord);
            // Вызвать метод проверки буквы
            char letter = getInputChar();
            // Вызываем метод, который обновляет скрытое слово
            updateHiddenWord(word, hiddenWord, letter);

            if (!updateHiddenWord(word, hiddenWord, letter)) {
                error++;
                maxAttempts--;
            }
        }

        if (new String(hiddenWord).contains("*")) {
            System.out.println("Вы проиграли! Правильное слово было: " + word);
        } else {
            System.out.println("Победа!");
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
        String randomWord;
        do {
            randomWord = listWords.get(random.nextInt(listWords.size()));
        } while (randomWord.length() < 5 || randomWord.length() > 10);
        return randomWord;
    }

    public static char getInputChar() {
        char letter;

        return scanner.next().toLowerCase().charAt(0);
    }
    public static void showGameState(int error, char[] arrHiddenChar) {
        System.out.println("Слово: " + new String(arrHiddenChar));
        System.out.println("Ошибки: " + error);
        System.out.println("Введите букву: ");
    }

    public static boolean updateHiddenWord(String word, char[] arrHiddenChar, char letter) {
        boolean isGuessed = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                arrHiddenChar[i] = letter;
                isGuessed = true;
            }
        }
        return isGuessed;
    }
}