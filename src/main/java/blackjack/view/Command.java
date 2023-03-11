package blackjack.view;

import java.util.Arrays;

public enum Command {

    HIT("y"),
    STAY("n");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command of(String command) {
        return Arrays.stream(Command.values())
                .filter(value -> value.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }

    public boolean isStay() {
        return this == Command.STAY;
    }
}
