package blackjack.model.card;

import java.util.regex.Pattern;

public class HitCommand {

    static final String ERROR_EMPTY_INPUT = "입력값은 공백일 수 없습니다.";
    static final String ERROR_NOT_Y_N_INPUT = "입력값은 y 또는 n만 가능합니다 : %s";
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
            throw new IllegalArgumentException(ERROR_EMPTY_INPUT);
        }
    }

    private void validateRegex(String command) {
        if (!Pattern.matches(Y_N_REGEX, command)) {
            throw new IllegalArgumentException(ERROR_NOT_Y_N_INPUT.formatted(command));
        }
    }
}
