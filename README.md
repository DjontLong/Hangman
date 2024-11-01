# Игра "Виселица"

Это простая реализация игры "Виселица" в процедурном стиле. Игра написана на Python и предназначена для запуска в консоли.

## Описание игры

При запуске приложения пользователю предлагается начать новую игру или выйти из приложения.

- **Начало новой игры:** Случайным образом выбирается слово, которое игрок должен отгадать. Игрок вводит буквы, пытаясь угадать слово.
- **Процесс игры:** После каждой введенной буквы в консоль выводится:
  - Счётчик ошибок
  - Текущее состояние виселицы (нарисованное с помощью ASCII символов)
  - Текущее состояние слова (с открытыми угаданными буквами)
- **Завершение игры:** После победы или поражения выводится соответствующее сообщение, и игрок снова получает выбор: начать новую игру или выйти из приложения

## Как запустить игру

1. Убедитесь, что у вас установлена Java Development Kit (JDK)
2. Склонируйте репозиторий или скачайте файл с кодом
3. Перейдите в корневую директорию проекта, где файл Main
4. Скомпилируйте и запустите файл с помощью Java:
   
     `javac HangmanGame.java`
   
     `java HangmanGame`
5. Следуйте инструкциям в консоли, чтобы играть
