package blackjack.domain;

import java.util.Arrays;

public enum Command {
    HIT("y"),
    BUST("n");


    private static final String INVALID_COMMAND = "올바른 명령어를 입력해주세요.";
    private final String command;

    Command(String command) {
        this.command = command;
    }

    private String getCommand() {
        return command;
    }

    public static Command findCommand(String command) {
        return Arrays.stream(values())
                .filter(x -> x.getCommand().equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND));
    }
}
