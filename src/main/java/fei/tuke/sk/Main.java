package fei.tuke.sk;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expr;

        while (true) {
            System.out.println("Enter an expression or type 'exit' to quit:");
            expr = scanner.nextLine();

            if (expr.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                int result = Parser.evaluate(expr);
                System.out.println("Result: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();

    }

}