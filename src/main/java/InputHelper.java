import java.util.Scanner;

public class InputHelper {

    public static int readInt(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scan.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid number");
            }
        }
    }

    public static int readInBetween(Scanner scan, String prompt, int min, int max) {
        while (true) {
            int number = readInt(scan, prompt);

            if (number >= min && number <= max) {
                return number;
            } else {
                System.out.println("Number must be between: " + min + " and " + max);
            }
        }
    }

    public static String readString(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scan.nextLine();

            if (!input.isBlank() && !input.contains(",") && input.length() <= 20) {
                return input;
            } else {
                System.out.println("Not a valid input");
            }
        }
    }

    public static Type readType(Scanner scan, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String type = scan.nextLine();

                return Type.valueOf(type.toUpperCase());

            } catch (IllegalArgumentException e) {
                System.out.println("That is not a valid Type");
            }
        }
    }
}