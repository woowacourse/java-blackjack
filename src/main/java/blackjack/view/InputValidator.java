package blackjack.view;

import java.util.List;
import java.util.regex.Matcher;

public class InputValidator {

    private static final String HIT_COMMAND_ERROR_MESSAGE = "y나 n을 입력해주세요";
    private static final String NULL_OR_BLANK_ERROR_MESSAGE = "null 또는 빈칸이 들어올 수 없습니다.";
    private static final List<String> COMMANDS = List.of("y", "n");

    public void validateInput(String input) {
        checkNull(input);
    }

    public void checkNull(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(NULL_OR_BLANK_ERROR_MESSAGE);
        }
    }

    public void checkHitCommand(String hitCommand) {
        if (!COMMANDS.contains(hitCommand)) {
            throw new IllegalArgumentException(HIT_COMMAND_ERROR_MESSAGE);

        }
    }

}
