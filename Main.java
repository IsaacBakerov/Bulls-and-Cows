package bullscows;

import java.util.*;

public class Main {

    public static int[] check(char[] realNum, char[] inputNum) {
        int bull = 0, cow = 0;
        for (int i = 0; i < realNum.length; i++) {
            for (int j = 0; j < inputNum.length; j++) {
                if (inputNum[i] == realNum[j]) {
                    cow++;
                }
            }

            if (inputNum[i] == realNum[i]) {
                bull++;
                cow--;
            }

        }
        int[] answer = {bull, cow};
        return answer;
    }

    public static String generateNumber(int length, int symbol) {
        StringBuilder secretNumber = new StringBuilder();
        char boundSymbol = (char) (symbol + 87);
        int i = 0;
        while (i < length) {
            Random random = new Random();
            int num = random.nextInt(symbol);
            if (secretNumber.indexOf(Integer.toString((char) num)) == -1) {
                if (num > 9) {
                    char c = (char) (num + 87);
                    secretNumber.append(c);
                } else {
                    secretNumber.append(num);
                }
                i++;
            } else {
                continue;
            }
        }
        System.out.println(secretNumber);

        System.out.print("The secret is prepared: ");
        int j = 0;
        while (j < length) {
            System.out.print("*");
            j++;
        }
        if (symbol <= 10) {
            System.out.println(" (0-" + (symbol-1) + ").");
        } else {
            System.out.println(" (0-9, a-" + (char) (boundSymbol-1) + ").");
        }
        return secretNumber.toString();
    }

    public static void main(String[] args) {
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        Scanner sc = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        String firstInput = sc.nextLine();
        int tr = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (firstInput.charAt(0) == numbers[i]) {
                tr++;
            }
        }

        if (tr == 0) {
            System.out.println("Error: \"abc 0 -7\" isn't a valid number.");
            System.exit(0);
        }

        int length = Integer.parseInt(firstInput);


        System.out.println("Input the number of possible symbols in the code:");
        int symbol = Integer.parseInt(sc.nextLine());


        StringBuilder secretNumber = new StringBuilder();
        char[] realNum = new char[length];

        if (length >= 10 || length <= 0) {
            System.out.println("Error: can't generate a secret number with a length of " + length + " because there aren't enough unique digits.");
        } else if (symbol > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        } else if (symbol < length) {
            System.out.println("Error: it's not possible to generate a code with a length of " + length + " with " + symbol + " unique symbols.");
        } else {
            secretNumber.append(generateNumber(length, symbol));

            System.out.println("Okay, let's start a game!");

            boolean end = true;
            while (end) {
                String input = sc.nextLine();
                char[] inputNum = new char[input.length()];
                for (int i = 0; i < input.length(); i++) {
                    inputNum[i] = input.charAt(i);
                    realNum[i] = secretNumber.charAt(i);
                }

                int bull = check(realNum, inputNum)[0];
                int cow = check(realNum, inputNum)[1];

                if (bull == secretNumber.length()) {
                    System.out.println("Grade: " + bull + " bulls");
                    System.out.println("Congratulations! You guessed the secret code.");
                    end = false;
                } else if (bull == 0 && cow <= 0) {
                    System.out.println("Grade: None.");
                } else if (bull == 0) {
                    if (cow == 1) {
                        System.out.println("Grade: " + cow + " cow");
                    } else {
                        System.out.println("Grade: " + cow + " cows");
                    }
                } else if (cow <= 0) {
                    if (bull == 1) {
                        System.out.println("Grade: " + bull + " bull");
                    } else {
                        System.out.println("Grade: " + bull + " bulls");
                    }
                } else {
                    if (bull == 1 && cow == 1) {
                        System.out.println("Grade: " + bull + " bull and " + cow + " cow");
                    } else if (bull == 1) {
                        System.out.println("Grade: " + bull + " bull and " + cow + " cows");
                    } else if (cow == 1) {
                        System.out.println("Grade: " + bull + " bulls and " + cow + " cow");
                    } else {
                        System.out.println("Grade: " + bull + " bulls and " + cow + " cows");
                    }
                }
            }
        }
    }
}
