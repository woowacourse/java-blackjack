package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String INPUT_Y_OR_N_ERROR_GUIDE_MESSAGE = "[ERROR] y 혹은 n 을 입력해야 합니다.";

    private InputView() {}

    public List<String> readPlayersName() {
        String input = scanner.nextLine();
        String[] split = input.split(DELIMITER);
        return Arrays.stream(split)
                .map(String::strip)
                .collect(Collectors.toList());
    }

    public boolean readStand() {
        String input = scanner.nextLine().strip();
        validateStand(input);
        return input.equalsIgnoreCase(YES);
    }

    private void validateStand(String input) {
        if (input.equalsIgnoreCase(YES) || input.equalsIgnoreCase(NO)) {
            throw new IllegalArgumentException(INPUT_Y_OR_N_ERROR_GUIDE_MESSAGE);
        }
    }
}
