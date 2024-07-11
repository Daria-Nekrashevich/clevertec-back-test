# clevertec-back-test

# Тестовое задание 1: Приложение для формирования чека покупок

Это тестовое задание демонстрирует применение ООП и шаблонов проектирования на языке Java для создания приложения, которое формирует чек покупок на основе входных данных.

## Требования

1. **Java 21**
2. Приложение запускается из командной строки с помощью команды:
   ```
   java -cp out ./src/main/java/ru/clevertec/check/CheckRunner.java id-quantity discountCard=xxxx balanceDebitCard=xxxx
   ```
   Где:
   - `id-quantity` - идентификатор товара и его количество (например, 3-1 для товара с id=3 и количества 1)
   - `discountCard=xxxx` - номер дисконтной карты (четыре цифры)
   - `balanceDebitCard=xxxx` - баланс на дебетовой карте

## Компоненты приложения

- **main-класс:** `./src/main/java/ru/clevertec/check/CheckRunner.java`
- **Файлы для чтения:**
  - `./src/main/resources/products.csv`
  - `./src/main/resources/discountCards.csv`
- **Файл для записи результата:** `result.csv` (в корне проекта)

## Запуск приложения

1. **Сборка проекта:**
   - Убедитесь, что Java 21 установлена и доступна в системной переменной PATH.
   - Склонируйте репозиторий и перейдите в ветку `feature/entry-core`.

2. **Запуск через командную строку:**
   - Откройте терминал или командную строку.
   - Перейдите в корневую директорию проекта.

3. **Команда для запуска:**
   ```bash
   java -cp out ./src/main/java/ru/clevertec/check/CheckRunner.java id-quantity discountCard=xxxx balanceDebitCard=xxxx
   ```

## Пример использования

Пример команды:
```bash
java -cp out ru.clevertec.check.CheckRunner 7-1 1-5 7-8 discountCard=3333 balanceDebitCard=200
```

Эта команда сформирует CSV-файл `result.csv`, содержащий чек покупок на основе переданных данных.
```bash
Date;Time
2024-07-11;04:23:53

QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL
5;Milk;1,07$;0,54$;5,35$
9;Packed apples 1kg;2,78$;1,00$;25,02$

DISCOUNT CARD;DISCOUNT PERCENTAGE
3333;4%

TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT
30,37$;1,54$;28,83$

```
---
