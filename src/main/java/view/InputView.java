package view;

import common.ErrorMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER = ",";
    private static final List<String> HIT_OR_STAND = List.of("y", "n");
    private static final Scanner sc = new Scanner(System.in);

    public List<String> readNames() {
        String line = sc.nextLine();
        validateIsBlank(line);
        return Arrays.stream(line.split(DELIMITER)).toList();
    }

    public String readHitOrStand() {
        String input = sc.nextLine().trim();
        validateIsBlank(input);
        validateHitOrStandValue(input);
        return input;
    }

    private void validateIsBlank(String line) {
        if (line.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ALLOW_EMPTY_INPUT.getMessage());
        }
    }

    private void validateHitOrStandValue(String input) {
        if (!HIT_OR_STAND.contains(input)) {
            throw new IllegalArgumentException(ErrorMessage.HIT_OR_STAND_VALUE_MIS_MATCH.getMessage());
        }
    }
}
