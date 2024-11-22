import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ввод ФИО
        System.out.print("Введите ФИО: ");
        String fullName = sc.nextLine().trim();

        // Ввод даты рождения
        System.out.print("Введите дату рождения (дд.мм.гггг или дд/мм/гггг): ");
        String birthDateInput = sc.nextLine().trim().replace("/", ".");

        // Разделение ФИО
        String[] nameParts = fullName.split("\\s+");
        if (nameParts.length < 3) {
            System.out.println("Ошибка: ФИО должно содержать три части (Фамилия Имя Отчество).");
            return;
        }
        String lastName = nameParts[0];
        String firstName = nameParts[1];
        String patronymic = nameParts[2];

        // Инициалы
        String initials = String.format("%s %c.%c.", lastName, firstName.charAt(0), patronymic.charAt(0));

        // Определение пола
        String gender = opr(patronymic);

        // Определение возраста
        int age = edge(birthDateInput);
        String ageString = ages(age);

        // Вывод результатов
        System.out.println("Инициалы: " + initials);
        System.out.println("Пол: " + gender);
        System.out.println("Возраст: " + ageString);
    }



    //вычисления возраста
    private static int edge(String birthDateInput) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate birthDate = LocalDate.parse(birthDateInput, formatter);
            LocalDate currentDate = LocalDate.now();
            return Period.between(birthDate, currentDate).getYears();
        } catch (Exception e) {
            System.out.println("Ошибка: Неверный формат даты. Используйте дд.мм.гггг.");
            System.exit(1);
            return -1; // Никогда не будет достигнуто
        }
    }

    //определения пола
    private static String opr(String patronymic) {
        if (patronymic.endsWith("ич")) {
            return "Мужской";
        } else if (patronymic.endsWith("на")) {
            return "Женский";
        } else {
            return "ОПРЕДЕЛИТЬ_НЕ_УДАЛОСЬ";
        }
    }
    //форматирования возраста с правильным окончанием
    private static String ages(int age) {
        if (age % 10 == 1 && age % 100 != 11) {
            return age + " год";
        } else if (age % 10 >= 2 && age % 10 <= 4 && (age % 100 < 10 || age % 100 >= 20)) {
            return age + " года";
        } else {
            return age + " лет";
        }
    }
}
