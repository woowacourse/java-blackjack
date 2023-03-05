package blackjack.domain.player;

import java.util.Arrays;

public enum Command {
    YES("y"),
    STAY("n");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command of(String inputAnswer) {
        return Arrays.stream(Command.values())
                .filter(command -> command.command.equals(inputAnswer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n 으로 입력해주세요."));
    }

    public boolean isYes() {
        return this == YES;
    }
}
