//При старте, приложение предлагает начать новую игру или выйти из приложения
//При начале новой игры, случайным образом загадывается слово, и игрок начинает процесс по его отгадыванию
//После каждой введенной буквы выводим в консоль счётчик ошибок, текущее состояние виселицы (нарисованное ASCII символами)
//По завершении игры выводим результат (победа или поражение) и возвращаемся к состоянию #1 - предложение начать новую игру или выйти из приложения

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    private static final Path FILE_PATH = Paths.get("words.txt");
    private static ArrayList<String> listWords = new ArrayList<>();;

    public static void main(String[] args) {
        // Проверяем реализованный метод
        var words = getListWords();

        for (String word : words) {
            System.out.println(word);
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
    public static void getRandomWord() {}

    // Основной цикл игры
    public static void startGameLoop() {}
}