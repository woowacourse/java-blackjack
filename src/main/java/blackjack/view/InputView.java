package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = ",";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputString() {
        return SCANNER.nextLine().trim();
    }

    public static List<String> inputStringAndSplitByComma() {
        return Arrays.stream(SCANNER.nextLine().trim().split(DELIMITER))
                .collect(Collectors.toList());
    }
}
