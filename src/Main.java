import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws CalcException {
        Scanner s = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение");
        String input = s.nextLine();
        String result = calc(input);
        System.out.println("Результат: " + result);
    }

    public static String calc(String input) throws CalcException {
        String[] strings = input.split(" ");
        if (strings.length != 3) {
            throw new CalcException("Формат математической операции не удовлетворяет заданию");
        }
        String sign = strings[1];
        int number1;
        int number2;
        boolean isRoman1;
        boolean isRoman2;

        try {
            RomanNumeral roman = RomanNumeral.valueOf(strings[0]);
            number1 = roman.getNumber();
            isRoman1 = true;

        } catch (IllegalArgumentException e) {
            try {
                number1 = Integer.parseInt(strings[0]);
            } catch (NumberFormatException ex) {
                throw new CalcException("Недоступен ввод дробных чисел");
            }
            isRoman1 = false;
        }

        try {
            RomanNumeral roman = RomanNumeral.valueOf(strings[2]);
            number2 = roman.getNumber();
            isRoman2 = true;
        } catch (IllegalArgumentException e) {
            try {
                number2 = Integer.parseInt(strings[2]);
            } catch (NumberFormatException ex) {
                throw new CalcException("Недоступен ввод дробных чисел");
            }
            isRoman2 = false;
        }

        int result;
        if ((isRoman1 != isRoman2) || (number1 < 1) || (number1 >= 11) || (number2 < 1) || (number2 >= 11)) {
            throw new CalcException("Некорректный ввод");
        }

        switch (sign) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                result = number1 / number2;
                break;
            default:
                throw new CalcException("Неверный знак операции");
        }
        if (isRoman1) {
            if (result > 0) {
                RomanNumeral effect = RomanNumeral.convert(result);
                return effect.name();
            } else {
                throw new CalcException("Результат не может быть меньше или равен 0");
            }

        } else {
            return Integer.toString(result);
        }
    }
}