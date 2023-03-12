package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String NATURAL_NUMBER_REGEX = "^[1-9]+\\d*$";
    private static final String DELIMITER = "\\s*,\\s*";
    private static final String NOT_NATURAL_NUMBER_EXCEPTION_MESSAGE = "입력이 정수여야 합니다.";

    private static final Scanner SCANNER = new Scanner(System.in);

    public List<String> readPlayerNames() {
        final String nameInput = SCANNER.nextLine();
        return Arrays.stream(nameInput.split(DELIMITER, -1)).collect(Collectors.toUnmodifiableList());
    }

    public int readBettingMoneyAmount() {
        final String amountInput = SCANNER.nextLine();
        validateNaturalNumber(amountInput);
        return Integer.parseInt(amountInput);
    }

    private void validateNaturalNumber(final String input) {
        if (!input.matches(NATURAL_NUMBER_REGEX)) {
            throw new IllegalArgumentException(NOT_NATURAL_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public String readDrawOrStay() {
        return SCANNER.nextLine();
    }

}
