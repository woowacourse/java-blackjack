package view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String DELIMITER_USERNAME = ",";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> readPlayersName() {
        String input = scanner.nextLine();
        return List.of(input.split(DELIMITER_USERNAME));
    }
}
