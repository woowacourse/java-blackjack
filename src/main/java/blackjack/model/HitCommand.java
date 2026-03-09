package blackjack.model;

import static blackjack.model.ErrorMessage.ERROR_EMPTY_INPUT;
import static blackjack.model.ErrorMessage.ERROR_NOT_Y_N_INPUT;

import java.util.regex.Pattern;

public class CardAddCmd {

    private static final String Y_N_REGREX = "^[yYnN]$";

    private final String command;

    public CardAddCmd(String command) {
        validateEmpty(command);
        validateRegrex(command);
        this.command = command;
    }

    public boolean isY() {
        return command.equals("y") || command.equals("Y");
    }

    private void validateEmpty(String command) {
        if (command.isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY_INPUT.getErrorMessage());
        }
    }

    private void validateRegrex(String command) {
        if (!Pattern.matches(Y_N_REGREX, command)) {
            throw new IllegalArgumentException(ERROR_NOT_Y_N_INPUT.getErrorMessage());
        }
    }
}
