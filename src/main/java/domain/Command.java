package domain;

import java.util.Arrays;

public enum Command {
    PROCEED_COMMAND("y"),
    STOP_COMMAND("n");
    static final String INVALID_COMMAND_MESSAGE = "입력은 y 또는 n 이어야 합니다.";

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command from(final String value) {
        return Arrays.stream(Command.values())
                .filter(command -> command.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MESSAGE));
    }

    public boolean isProceed() {
        return PROCEED_COMMAND == this;
    }
}
