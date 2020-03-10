package blackjack.view;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> getNames() {
        String input = scanner.nextLine();
        return Arrays.asList(input.split(DELIMITER));
    }
}
