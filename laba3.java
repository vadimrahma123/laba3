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
        System.out.printf("=============");
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