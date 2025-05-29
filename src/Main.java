import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    
    static char[] actions = new char[]{'+', '-', '*', '/'};
    
    public static void main(String[] args) {
        System.out.println("Введите два целых числа от 1 до 10 и арифметический оператор:");
        Scanner console = new Scanner(System.in);
        String inPut = console.nextLine();
        try {
            System.out.println(calc(inPut));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    public static String calc(String input)  {
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
        
        if (type.equals("ArabicFrom1to10")) {
                int number1 = Integer.parseInt(firstNumber);
                int number2 = Integer.parseInt(secondNumber);
                
                int resultArabic = Result(number1, number2, regexAction);
                return String.valueOf(resultArabic);
        }
        
        throw new IllegalArgumentException("Введенные данные не являются целыми цифрами от 1 до 10");
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
            definition = "ArabicFrom1to10";
            return definition;
        }
        return definition;
    }
}