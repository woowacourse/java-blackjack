package blackjack.util;

import java.util.Scanner;
import java.util.function.Supplier;

public class ConsoleReader implements Supplier<String> {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String get() {
        return scanner.nextLine();
    }
}
