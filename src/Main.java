import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static final Path FILE_PATH = Paths.get("words.txt");
    private static ArrayList<String> listWords = new ArrayList<>();
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);
    private static int maxAttempts = 9;
    private static final String INCORRECT_INPUT = "Некорректный ввод. Повторите попытку!";
    private static final String GOODBYE = "Всего доброго";
    private static final String ERROR = "Произошла ошибка: ";
    private static final String WORDS_LOADED = "Слов загружено: ";
    private static final String NEW_GAME = "1. Новая игра";
    private static final String EXIT = "2. Выход";
    private static final String SELECT_OPTION = "Выберите опцию: ";
    private static final String YOU_LOST = "Вы проиграли! Правильное слово было: ";
    private static final String YOU_WIN = "Победа!";
    private static final String WORD = "Слово: ";
    private static final String ERRORS = "Ошибки: ";
    private static final String ENTER_LETTER = "Введите букву: ";
    private static final String NOT_FOUND = "Файл words.txt не найден";
    public static void main(String[] args) throws IOException {
        // Загружаем список слов
        listWords = getListWords();

        try {
            do {
                drawingStartMenu();
                int inputMenu = 0;

                try {
                    inputMenu = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(INCORRECT_INPUT);
                    scanner.next(); // Очищаем буфер сканера
                    continue;
                }

                switch (inputMenu) {
                    case 1:
                        startGameLoop();
                        break;
                    case 2:
                        System.out.println(GOODBYE);
                        return;
                    default:
                        System.out.println(INCORRECT_INPUT);
                        System.out.println();
                }
            } while (true);
        } catch (Exception e) {
            System.out.println(ERROR + e.getMessage());
        }
    }

    // Метод для чтения слов из файла и загрузка их в коллекцию
    public static ArrayList<String> getListWords() throws IOException {
        // Читаем файл построчно и добавляем в коллецию
        // Должны вернуть ArrayList со словами
        try (InputStream resource = Main.class.getClassLoader().getResourceAsStream("words.txt")) {
            if (resource == null) {
                System.out.println(NOT_FOUND);
                return listWords;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    listWords.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return listWords;
    }

    public static void drawingStartMenu() {
        System.out.println(WORDS_LOADED + listWords.size());
        System.out.println("=====================");
        System.out.println(NEW_GAME);
        System.out.println(EXIT);
        System.out.println("=====================");
        System.out.print(SELECT_OPTION);
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
            // В случае ошибки вызываем метод состояния виселицы
            showGallows(error);
        }

        if (new String(hiddenWord).contains("*")) {
            System.out.println(YOU_LOST + word);
            System.out.println();
        } else {
            System.out.println(YOU_WIN);
            System.out.println();
        }
    }

    // Метод для получения рандомного слова из коллекции
    public static String getRandomWord() {
        String randomWord;
        do {
            randomWord = listWords.get(random.nextInt(listWords.size()));
        } while (randomWord.length() < 5 || randomWord.length() > 10);
        return randomWord;
    }

    public static void showGameState(int error, char[] arrHiddenChar) {
        System.out.println(WORD + new String(arrHiddenChar));
        System.out.println(ERRORS + error);
        System.out.println(ENTER_LETTER);
    }

    public static char getInputChar() {
        return scanner.next().toLowerCase().charAt(0);
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

    public static void showGallows(int error) {
        switch (error) {
            case 1:
                System.out.println(" -----\n" +
                        " |   |\n" +
                        " |\n" +
                        " |\n" +
                        " |\n" +
                        " |\n" +
                        "_|_\n");
                break;
            case 2:
                System.out.println(" -----\n" +
                        " |   |\n" +
                        " |   O\n" +
                        " |\n" +
                        " |\n" +
                        " |\n" +
                        "_|_\n");
                break;
            case 3:
                System.out.println(" -----\n" +
                        " |   |\n" +
                        " |   O\n" +
                        " |   |\n" +
                        " |\n" +
                        " |\n" +
                        "_|_\n");
                break;
            case 4:
                System.out.println(" -----\n" +
                        " |   |\n" +
                        " |   O\n" +
                        " |  /|\n" +
                        " |\n" +
                        " |\n" +
                        "_|_\n");
                break;
            case 5:
                System.out.println(" -----\n" +
                        " |   |\n" +
                        " |   O\n" +
                        " |  /|\\\n" +
                        " |\n" +
                        " |\n" +
                        "_|_\n");
                break;
            case 6:
                System.out.println(" -----\n" +
                        " |   |\n" +
                        " |   O\n" +
                        " |  /|\\\n" +
                        " |  /\n" +
                        " |\n" +
                        "_|_\n");
                break;
            case 7:
                System.out.println(" -----\n" +
                        " |   |\n" +
                        " |   O\n" +
                        " |  /|\\\n" +
                        " |  / \\\n" +
                        " |\n" +
                        "_|_\n");
                break;
            case 8:
                System.out.println(" -----\n" +
                        " |   |\n" +
                        " |   O\n" +
                        " |  /|\\\n" +
                        " |  / \\_\n" +
                        " |    \n" +
                        "_|_\n");
                break;
            case 9:
                System.out.println(" -----\n" +
                        " |   |\n" +
                        " |   O\n" +
                        " |  /|\\\n" +
                        " | _/ \\_\n" +
                        " |    \n" +
                        "_|_\n");
                break;
        }
    }
}