package blackjack.domain;

import java.util.Arrays;

public enum Command {
    HIT("y"),
    BUST("n");

    private static final String INVALID_COMMAND_MESSAGE = "올바른 명령어가 아닙니다.";

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static boolean isHit(String command) {
        validate(command);
        return HIT.getCommand().equals(command);
    }

    public static void validate(String input) {
        if (!isValidCommand(input)) {
            throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
        }
    }

    private static boolean isValidCommand(String input) {
        return Arrays.stream(values()).anyMatch(x -> x.command.equals(input));
    }

    private String getCommand() {
        return command;
    }
}
