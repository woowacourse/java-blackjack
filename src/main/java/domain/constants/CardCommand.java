package domain.constants;

import java.util.Arrays;

public enum CardCommand {
    HIT("y"),
    STAND("n");

    private final String command;

    CardCommand(final String command) {
        this.command = command;
    }

    public static CardCommand from(String command) {
        return Arrays.stream(CardCommand.values())
                .filter(element -> element.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 응답입니다."));
    }
}
