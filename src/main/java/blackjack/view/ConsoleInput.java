package blackjack.view;

import java.util.Scanner;
import java.util.function.Supplier;

public class ConsoleInput {

    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleInput() {}

    public static String readLine() {
        return SCANNER.nextLine().strip();
    }

    public static <T> T readWithRetry(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
