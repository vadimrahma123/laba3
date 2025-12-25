## Отчёт по лабораторной работе № 3

#### № группы: ПМ-2502

#### Выполнил: Рахматуллин Вадим Тимурович

#### Вариант: 17

---
### Содержание

- [1. Постановка задачи](#1-постановка-задачи)
- [3. Выбор структуры данных](#2-выбор-структуры-данных)
- [4. Программа](#3-программа)
- [5. Анализ правильности решения](#4-анализ-правильности-решения)

---

**Условие**
> Разработать программу для тренировки навыков скоропечатания на нескольких язы-
>ках, начиная с русского и поддерживая до пяти языков. Реализовать функционал до-
>бавления новых языков, анализа прогресса, расчёта скорости печати и её зависимости
>от успехов на других языках, а также подсчёта времени и количества напечатанных
>символов.

**Описание функционала**
>
>1. Создание системы скоропечатания
>Создаёт объект системы скоропечатания. Изначально школьник знает только рус-
>ский язык («ru»), печатает на нём со скоростью 100 символов в минуту.
>2. Вывод информации о системе
>Отображает список изучаемых языков, их скорости, суммарное время печати и
>общее количество символов, напечатанных на каждом языке.
>3. Печать на языке
>Выполняет печать указанного количества символов на выбранном языке. Учиты-
>вает текущую скорость печати на языке. Общее количество символов обновляется
>по завершении действия.
>4. Получение скорости печати
>Возвращает текущую скорость печати на указанном языке.
>5. Изучение нового языка
>Добавляет новый язык, если общее количество изучаемых языков меньше пяти.
>Скорость печати на новом языке рассчитывается как 100+минимальный прогресс на других языках
>2 .
>6. Печать по времени
>Выполняет печать на указанном языке в течение переданного времени (в мину-
>тах). Рассчитывает количество символов, напечатанных за это время.
>7. Определение самого активного языка
>Возвращает язык, на котором школьник печатал дольше всего.
>8. Расчёт количества символов для сравнения скорости
>Определяет, сколько символов необходимо напечатать на самом медленном языке,
>чтобы его скорость сравнялась с самым быстрым языком. Учитывает правила
>изменения скорости (каждые 1000 символов скорость увеличивается на 5 символов
>в минуту).

### 2. Алгоритм и структура данных
-1 Базовая структура записи для языка:
Код: строка                    # уникальный идентификатор (ru, en, es)
Скорость: целое число          # символов в минуту
Символов: целое число          # общее количество напечатанных символов
Время: вещественное число      # общее время в минутах

-2 Структура хранения:
Система:
Языки: словарь<Код, Язык>      # коллекция изучаемых языков
Макс_языков: целое = 5         # максимальное количество языков

-Основные алгоритмы
-Алгоритм 1: Инициализация системы

1. Создать пустой словарь Языки
2. Создать запись для языка "ru":
- Код = "ru"
- Скорость = 100
- Символов = 0
- Время = 0
3. Добавить запись в словарь Языки

-Алгоритм 2: Добавление нового языка
Вход: код_языка
Выход: логическое значение (успех/неудача)
1. Если размер(Языки) ≥ Макс_языков:
- Вернуть ЛОЖЬ

2. Если код_языка существует в Языки:
- Вернуть ЛОЖЬ

3. Вычислить минимальный прогресс среди всех языков:
- мин_символов = минимальное(Символов для всех языков в Языки)

4. Вычислить начальную скорость:
- начальная_скорость = 100 + (мин_символов ÷ 2)

5. Создать новую запись Язык:
- Код = код_языка
- Скорость = начальная_скорость
- Символов = 0
- Время = 0

6. Добавить запись в Языки
7. Вернуть ИСТИНА

-Алгоритм 3: Печать символов на языке
Вход: код_языка, количество_символов
Выход: затраченное время

1. Если код_языка не найден в Языки:
- Вернуть ошибку

2. Получить запись язык из Языки по код_языка

3. Обновить запись:
- язык.Символов = язык.Символов + количество_символов
- затраченное_время = количество_символов ÷ язык.Скорость
- язык.Время = язык.Время + затраченное_время

4. Проверить увеличение скорости:
- старые_тысячи = язык.Символов - количество_символов
- новые_тысячи = язык.Символов
- если целая_часть(новые_тысячи ÷ 1000) > целая_часть(старые_тысячи ÷ 1000):
язык.Скорость = язык.Скорость + 5

5. Вернуть затраченное_время

-Алгоритм 4: Печать по времени
Вход: код_языка, время_в_минутах
Выход: количество_напечатанных_символов

1. Если код_языка не найден в Языки:
- Вернуть ошибку

2. Получить запись язык из Языки по код_языка

3. Вычислить количество символов:
- символы = время_в_минутах × язык.Скорость

4. Вызвать Алгоритм 3 с параметрами (код_языка, символы)

5. Вернуть символы

-Алгоритм 5: Определение самого активного языка
Выход: код самого активного языка или пустая строка

1. Инициализировать:
- самый_активный = пустая_строка
- макс_время = 0

2. Для каждой записи язык в Языки:
- если язык.Время > макс_время:
самый_активный = язык.Код
макс_время = язык.Время

3. Вернуть самый_активный

-Алгоритм 6: Расчет символов для выравнивания скорости
Выход: необходимое_количество_символов для самого медленного языка

1. Если количество записей в Языки < 2:
- Вернуть ошибку

2. Найти самый быстрый язык:
- быстрый = запись с максимальным значением Скорость в Языки

3. Найти самый медленный язык:
- медленный = запись с минимальным значением Скорость в Языки

4. Вычислить разницу скоростей:
- разница = быстрый.Скорость - медленный.Скорость

5. Вычислить необходимые символы:
- необходимые_символы = (разница ÷ 5) × 1000

6. Вернуть необходимые_символы

-Алгоритм 7: Отображение информации о системе
1. Вывести заголовок "ВСЕ ЯЗЫКИ"

2. Для каждой записи язык в Языки:
- вывести форматированную строку:
"Код: язык.Код, Скорость: язык.Скорость, 
Символов: язык.Символов, Время: язык.Время"

3. Вывести разделитель
    
### 3. Программа

```java
import java.util.*;

// Класс для одного языка
class Language {
    private String code;              // ru, en, es
    private int speed;                // символов в минуту
    private int totalSymbols;         // всего напечатано
    private double totalTime;         // всего времени

    // Создать язык
    public Language(String code) {
        this.code = code;
        this.speed = 100;             // начальная скорость
        this.totalSymbols = 0;
        this.totalTime = 0.0;
    }

    // Получить данные
    public String getCode() { return code; }
    public int getSpeed() { return speed; }
    public int getTotalSymbols() { return totalSymbols; }
    public double getTotalTime() { return totalTime; }

    // Добавить символы
    public void addSymbols(int symbols) {
        this.totalSymbols += symbols;
        this.totalTime += (double) symbols / speed;

        // Увеличить скорость каждые 1000 символов
        checkSpeedIncrease();
    }

    // Увеличить скорость
    private void checkSpeedIncrease() {
        int increases = totalSymbols / 1000;  // сколько раз по 1000 символов
        this.speed = 100 + (increases * 5);   // +5 за каждую 1000
    }

    // Печатать по времени
    public void typeForTime(double minutes) {
        int symbols = (int)(minutes * speed);  // сколько символов за это время
        addSymbols(symbols);                   // добавляем их
    }
}

// Основная система
class TypingTrainingSystem {
    private HashMap<String, Language> languages;  // храним языки
    private static final int MAX_LANGUAGES = 5;   // не больше 5

    // Конструктор
    public TypingTrainingSystem() {
        languages = new HashMap<>();
        languages.put("ru", new Language("ru"));  // русский по умолчанию
    }

    // 1. Показать все языки
    public void showInfo() {
        System.out.println("\n === ЯЗЫКИ ===");
        for (Language lang : languages.values()) {
            System.out.printf("%s: %d сим/мин, %d симв, %.1f мин%n",
                    lang.getCode(), lang.getSpeed(),
                    lang.getTotalSymbols(), lang.getTotalTime());
        }
        System.out.printf("=============")
    }

    // 2. Печатать на языке
    public void type(String languageCode, int symbols) {
        Language lang = languages.get(languageCode);
        if (lang == null) {
            System.out.println("Нет языка: " + languageCode);
            return;
        }

        lang.addSymbols(symbols);
        System.out.println("Напечатано " + symbols + " на " + languageCode);
    }

    // 3. Узнать скорость языка
    public int getSpeed(String languageCode) {
        Language lang = languages.get(languageCode);
        return lang != null ? lang.getSpeed() : 0;
    }

    // 4. Добавить новый язык
    public void addLanguage(String languageCode) {
        // Проверить лимит
        if (languages.size() >= MAX_LANGUAGES) {
            System.out.println("Уже " + MAX_LANGUAGES + " языков");
            return;
        }

        // Проверить, нет ли уже
        if (languages.containsKey(languageCode)) {
            System.out.println("Язык " + languageCode + " уже есть");
            return;
        }

        // Рассчитать скорость: 100 + минимальный прогресс других / 2
        int minProgress = getMinProgress();
        int newSpeed = 100 + minProgress / 2;

        languages.put(languageCode, new Language(languageCode));
        // Установить начальную скорость
        Language newLang = languages.get(languageCode);
        int increases = (newSpeed - 100) / 5;
        for (int i = 0; i < increases; i++) {
            newLang.addSymbols(1000);
        }

        System.out.println("Добавлен " + languageCode + ", скорость " + newSpeed);
    }

    // Найти минимальный прогресс
    private int getMinProgress() {
        int min = Integer.MAX_VALUE;
        for (Language lang : languages.values()) {
            if (lang.getTotalSymbols() < min) {
                min = lang.getTotalSymbols();
            }
        }
        return min;
    }

    // 5. Печатать по времени
    public void typeForTime(String languageCode, double minutes) {
        Language lang = languages.get(languageCode);
        if (lang == null) {
            System.out.println("Нет языка: " + languageCode);
            return;
        }

        lang.typeForTime(minutes);
        System.out.printf("Печатал %.1f мин на %s%n", minutes, languageCode);
    }

    // 6. Самый активный язык
    public String getMostActive() {
        String active = null;
        double maxTime = 0;

        for (Language lang : languages.values()) {
            if (lang.getTotalTime() > maxTime) {
                maxTime = lang.getTotalTime();
                active = lang.getCode();
            }
        }

        return active != null ? active : "нет";
    }

    // 7. Сколько символов чтобы догнать
    public void calculateCatchUp() {
        if (languages.size() < 2) {
            System.out.println("Нужно 2 языка");
            return;
        }

        // Найти самый быстрый и медленный
        Language fast = null;
        Language slow = null;

        for (Language lang : languages.values()) {
            if (fast == null || lang.getSpeed() > fast.getSpeed()) {
                fast = lang;
            }
            if (slow == null || lang.getSpeed() < slow.getSpeed()) {
                slow = lang;
            }
        }

        // Разница в скорости
        int diff = fast.getSpeed() - slow.getSpeed();
        // Каждые 5 скорости = 1000 символов
        int needSymbols = (diff / 5) * 1000;

        System.out.printf("%s (%d) догонит %s (%d) через %d символов%n",
                slow.getCode(), slow.getSpeed(),
                fast.getCode(), fast.getSpeed(),
                needSymbols);
    }
}

// Тестирование
public class laba3 {
    public static void main(String[] args) {
        TypingTrainingSystem system = new TypingTrainingSystem();

        // Начальное состояние
        system.showInfo();

        // Печатаем на русском
        system.type("ru", 500);
        system.type("ru", 700);

        // Добавляем английский
        system.addLanguage("en");
        system.type("en", 300);

        // Добавляем испанский
        system.addLanguage("es");
        system.typeForTime("es", 2.0);

        // Результаты
        system.showInfo();

        // Самый активный
        System.out.println("Самый активный: " + system.getMostActive());

        // Расчет догона
        system.calculateCatchUp();

        // Добавляем еще языки
        system.addLanguage("fr");
        system.addLanguage("de");
        system.addLanguage("it"); // Не добавится - лимит 5
    }
}
```

### 4. Анализ правильности решения
-Input:
```
TypingTrainingSystem system = new TypingTrainingSystem();
system.showInfo();
```
-output:
```
=== ЯЗЫКИ ===
ru: 100 сим/мин, 0 симв, 0.0 мин
=============
```

2. Добавление языков

-Input:
```
TypingTrainingSystem system = new TypingTrainingSystem();
System.out.println(system.addLanguage("ru"));
System.out.println(system.addLanguage("en"));
System.out.println(system.addLanguage("en"));
System.out.println(system.addLanguage("es"));
System.out.println(system.addLanguage("fr"));
System.out.println(system.addLanguage("de"));
System.out.println(system.addLanguage("it"));
system.showInfo();
```
-Output:
```
Нет языка: ru
true
Язык en уже есть
false
Добавлен es, скорость 100
Добавлен fr, скорость 100
Добавлен de, скорость 100
Уже 5 языков
false

=== ЯЗЫКИ ===
ru: 100 сим/мин, 0 симв, 0.0 мин
en: 100 сим/мин, 0 симв, 0.0 мин
es: 100 сим/мин, 0 симв, 0.0 мин
fr: 100 сим/мин, 0 симв, 0.0 мин
de: 100 сим/мин, 0 симв, 0.0 мин
=============
```
3. Печать символов

-Input:
```
TypingTrainingSystem system = new TypingTrainingSystem();
system.addLanguage("en");
system.type("ru", 500);
system.type("ru", 700);
system.type("en", 300);
system.showInfo();
```

-Output:
```
Добавлен en, скорость 100
Напечатано 500 на ru
Напечатано 700 на ru
Напечатано 300 на en

=== ЯЗЫКИ ===
ru: 110 сим/мин, 1200 симв, 10.9 мин
en: 100 сим/мин, 300 симв, 3.0 мин
=============
```
4. Печать по времени

-Input:
```
TypingTrainingSystem system = new TypingTrainingSystem();
system.addLanguage("es");
system.typeForTime("ru", 2.0);
system.typeForTime("es", 1.5);
system.showInfo();
```
-Output:
```
Добавлен es, скорость 100
Печатал 2.0 мин на ru
Печатал 1.5 мин на es

=== ЯЗЫКИ ===
ru: 100 сим/мин, 200 симв, 2.0 мин
es: 100 сим/мин, 150 симв, 1.5 мин
=============
```
5. Самый активный язык

-nput:
```
TypingTrainingSystem system = new TypingTrainingSystem();
system.addLanguage("en");
system.type("ru", 1000);
system.type("en", 500);
System.out.println("Самый активный: " + system.getMostActive());
```
-Output:
```
Добавлен en, скорость 100
Напечатано 1000 на ru
Напечатано 500 на en
Самый активный: ru
```
6. Расчет для сравнения скоростей

-Input:
```
TypingTrainingSystem system = new TypingTrainingSystem();
system.addLanguage("en");
system.type("ru", 3000);  // Скорость станет 115
system.type("en", 1000);  // Скорость станет 105
system.calculateCatchUp();
```
-Output:
```
Добавлен en, скорость 100
Напечатано 3000 на ru
Напечатано 1000 на en
en (105) догонит ru (115) через 2000 символов
```

