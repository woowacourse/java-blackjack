package team.blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static String readInput() {
        return scanner.nextLine();
    }

    public static List<String> readPlayerNames() {
        return Arrays.stream(readInput().replaceAll("\s", "").split(","))
                .toList();
    }

    public static String readHitDecision() {
        return readInput();
    }
}
