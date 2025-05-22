import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    
    static char[] actions = new char[]{'+', '-', '*', '/'};
    
    public static void main(String[] args) {
        System.out.println("Введите два арабских или римских числа от 1 до 10 и арифметический оператор:");
        Scanner console = new Scanner(System.in);
        String inPut = console.nextLine();
        try {
            System.out.println(calc(inPut));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    public static String calc(String input) throws IllegalArgumentException {
        char[] myArray = input.toCharArray();
        int quantityAction = 0;
        for (int i = 0; i < actions.length; i++) {
            for (int j = 0; j < myArray.length; j++) {
                if (myArray[j] == actions[i]) {
                    quantityAction++;
                }
            }
        }
        
        if (quantityAction != 1) {
            throw new IllegalArgumentException("В введенной строке или нет арифметических операций или их больше чем одна");
        }
        
        String regexAction = "";
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i] == '+') {
                regexAction = "\\+";
            } else if (myArray[i] == '-') {
                regexAction = "-";
            } else if (myArray[i] == '*') {
                regexAction = "\\*";
            } else if (myArray[i] == '/') {
                regexAction = "/";
            }
        }
        
        String[] twoBlock = input.split(regexAction);
        
        if (twoBlock.length != 2) {
            throw new IllegalArgumentException("В введенной строке нет двух чисел");
        }
        
        String firstNumber = twoBlock[0].trim();
        String secondNumber = twoBlock[1].trim();
        
        String type = definition(firstNumber, secondNumber);
        
        if (type.equals("Arabic")) {
            System.out.println("Вы ввели АРАБСКИЕ числа от 1 до 10.");
                int number1 = Integer.parseInt(firstNumber);
                int number2 = Integer.parseInt(secondNumber);
                
                int resultArabic = Result(number1, number2, regexAction);
                return String.valueOf(resultArabic);
        }
        
        if(type.equals("Roman")) {
            int number1 = searchIndexRoman(firstNumber);
            int number2 = searchIndexRoman(secondNumber);
            
            int result = Result(number1, number2, regexAction);
            if(result > 0){
                System.out.println("Вы ввели РИМСКИЕ числа от 1 до 10.");
                return convertNumToRoman(result);
            } else {
                throw new IllegalArgumentException("Результат при римских числах может быть только больше 0");
            }
        }
        
        throw new IllegalArgumentException("Введенные данные не являются или только АРАБСКИМИ или только РИМСКИМИ цифрами от 1 до 10");
    }
    
    
    public static int Result(int number1, int number2, String regexAction) {
        int result = 0;
        switch (regexAction) {
            case "\\+" : result = number1 + number2;
                break;
            case "-" : result = number1 - number2;
                break;
            case "\\*" : result = number1 * number2;
                break;
            case "/" : result = number1 / number2;
                break;
        }
        return result;
    }
    
    public static String definition(String firstNumber, String secondNumber) {
        String definition = "null";
        String arabicNumbers = "([1-9]|10)";
        
        Pattern patternArabic = Pattern.compile(arabicNumbers);
        Matcher FirstNumberArabic = patternArabic.matcher(firstNumber);
        Matcher SecondNumberArabic = patternArabic.matcher(secondNumber);
        boolean matchArabicNumbersLeft = FirstNumberArabic.matches();
        boolean matchArabicNumbersRight = SecondNumberArabic.matches();
        
        if (matchArabicNumbersLeft && matchArabicNumbersRight) {
            definition = "Arabic";
            return definition;
        }
        
        String romanNumbers = "I|II|III|IV|V|VI|VII|VIII|IX|X";
        Pattern patternRoman = Pattern.compile(romanNumbers);
        Matcher FirstNumberRoman = patternRoman.matcher(firstNumber);
        Matcher SecondNumberRoman = patternRoman.matcher(secondNumber);
        boolean matchRomanNumbersLeft = FirstNumberRoman.matches();
        boolean matchRomanNumbersRight = SecondNumberRoman.matches();
        
        if (matchRomanNumbersLeft && matchRomanNumbersRight) {
            definition = "Roman";
            return definition;
        }
        
        return definition;
    }
    
    public static String convertNumToRoman(int arabicNumbers) {
        String[] roman = {
                "O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",
                "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return roman[arabicNumbers];
    }
    
    public static int searchIndexRoman(String RomanNumber) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (int i = 0; i < roman.length; i++) {
            if (roman[i].equals(RomanNumber)) {
                return i;
            }
        }
        return -1;
    }
}