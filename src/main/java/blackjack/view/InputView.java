package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        return List.of(scanner.nextLine().split(","));
    }
}
