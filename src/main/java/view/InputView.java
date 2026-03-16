package view;

import domain.participant.Name;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_EMPTY_ERROR = "빈 값을 입력할 수 없습니다.";
    private static final String INVALID_TYPE = "정수가 아닙니다.";
    private static final String INVALID_HIT_STAND_INPUT_ERROR = "y 또는 n만 입력 가능합니다.";

    Scanner scanner = new Scanner(System.in);

    public List<Name> readPlayers() {
        final String input = scanner.nextLine();

        validateIsBlank(input);

        return InputParser.parsePlayers(input);
    }

    public int readBetAmount() {
        final String input = scanner.nextLine();

        validateIsBlank(input);

        try {
            return Integer.parseInt(input);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_TYPE);
        }
    }

    public boolean readHitOrStand() {
        String input = scanner.nextLine();

        validateIsBlank(input);
        input = input.trim();

        if (!input.matches("[yn]")) {
            throw new IllegalArgumentException(INVALID_HIT_STAND_INPUT_ERROR);
        }
        return "y".equals(input);
    }


    private static void validateIsBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INPUT_EMPTY_ERROR);
        }
    }
}
