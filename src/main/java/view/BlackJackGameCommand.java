package view;

import java.util.Arrays;

public enum BlackJackGameCommand {
    YES("y"),
    NO("n");

    private final String command;

    BlackJackGameCommand(final String command) {
        this.command = command;
    }

    public static BlackJackGameCommand from(final String command) {
        return Arrays.stream(values())
                .filter(value -> command.equals(value.command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "%s또는 %s을 입력해주세요.".formatted(YES.command, NO.command)));
    }

    public boolean yes() {
        return this == YES;
    }
}
