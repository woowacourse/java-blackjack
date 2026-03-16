package view;

import static exception.ErrorMessage.BET_AMOUNT_OUT_OF_RANGE;
import static exception.ErrorMessage.INPUT_EMPTY_ERROR;
import static exception.ErrorMessage.INVALID_HIT_STAND_INPUT_ERROR;

import domain.participant.Name;
import java.util.List;
import java.util.Scanner;

public class InputView {
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
            throw new IllegalArgumentException(BET_AMOUNT_OUT_OF_RANGE.getMessage());
        }
    }

    public boolean readHitOrStand() {
        String input = scanner.nextLine();

        validateIsBlank(input);
        input = input.trim();

        if (!input.matches("[yn]")) {
            throw new IllegalArgumentException(INVALID_HIT_STAND_INPUT_ERROR.getMessage());
        }
        return "y".equals(input);
    }


    private static void validateIsBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INPUT_EMPTY_ERROR.getMessage());
        }
    }
}
