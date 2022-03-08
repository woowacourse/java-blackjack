package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String NAME_DELIMITER = ",";

    public static List<String> requestNames() {
        String input = readLine();
        return Arrays.asList(input.split(NAME_DELIMITER));
    }

    private static String readLine() {
        return scanner.nextLine();
    }
}
