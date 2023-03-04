package blackjack.domain;

import java.util.Arrays;

public enum Command {
    CONTINUE("y"),
    END("n");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command toCommand(String reply) {
        return Arrays.stream(Command.values()).filter((command -> command.value.equals(reply)))
            .findFirst().orElseThrow(() -> {
                throw new IllegalArgumentException("올바르지 않는 명령어입니다.");
            });
    }

    public static boolean isContinue(Command command) {
        return command == CONTINUE;
    }
}
