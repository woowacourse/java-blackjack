package blackjack;

import java.util.Arrays;

public enum Command {
    YES("y"),
    NO("n");

    private String value;

    Command(String value) {
        this.value = value;
    }

    public static Command find(String command) {
        return Arrays.stream(Command.values())
                .filter(it -> it.value.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다."));
    }
}
