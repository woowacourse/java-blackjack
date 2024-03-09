package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NAME_SPLIT_DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        String input = scanner.nextLine();
        String[] names = input.split(NAME_SPLIT_DELIMITER);
        return Arrays.stream(names)
                .map(String::trim)
                .toList();
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
