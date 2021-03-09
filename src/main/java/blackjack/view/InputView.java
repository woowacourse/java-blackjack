package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    private InputView() {
    }

    public static List<String> inputPlayers() {
        return Arrays.asList(scanner.nextLine().split(DELIMITER));
    }

    public static String getHitValue() {
        return scanner.nextLine().toUpperCase();
    }
}
