package blackjack.model;

import static blackjack.model.constant.ErrorMessage.ERROR_EMPTY_INPUT;
import static blackjack.model.constant.ErrorMessage.ERROR_NOT_Y_N_INPUT;

import java.util.regex.Pattern;

public class HitCommand {

    private static final String Y_N_REGEX = "^[yYnN]$";

    private final String command;

    public HitCommand(String command) {
        validateEmpty(command);
        validateRegex(command);
        this.command = command;
    }

    public boolean isY() {
        return command.equals("y") || command.equals("Y");
    }

    private void validateEmpty(String command) {
        if (command.isBlank()) {
            throw new IllegalArgumentException(ERROR_EMPTY_INPUT.getErrorMessage());
        }
    }

    private void validateRegex(String command) {
        if (!Pattern.matches(Y_N_REGEX, command)) {
            throw new IllegalArgumentException(ERROR_NOT_Y_N_INPUT.getErrorMessage());
        }
    }
}
