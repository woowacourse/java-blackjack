package blackjack.view;

import java.util.Arrays;

public enum PlayerCommand {
    HIT("y"),
    STAND("n");

    private static final String NOT_EXIST_COMMAND_ERROR = "존재하지 않는 명령어 입니다.";

    private final String command;

    PlayerCommand(String command) {
        this.command = command;
    }

    public static PlayerCommand from(String command) {
        return Arrays.stream(values())
                .filter(progress -> progress.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_COMMAND_ERROR));
    }
}
