package blackjack.game;

import java.util.Arrays;

public enum Command {
    YES("y"),
    NO("n");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command from(String value) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 명령어입니다."));
    }

    public boolean isNo() {
        return this == NO;
    }
}
