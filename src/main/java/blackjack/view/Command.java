package blackjack.view;

import java.util.Arrays;

public enum Command {
    HIT("y"),
    STAY("n");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command findCommand(String value) {
        return Arrays.stream(Command.values())
                .filter(command -> command.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 명령어를 입력해야합니다."));
    }

    public String getValue() {
        return value;
    }

    public boolean isStay() {
        return this == STAY;
    }
}
