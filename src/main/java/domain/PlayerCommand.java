package domain;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum PlayerCommand {

    HIT("y"),
    STAND("n");

    private static final String COMMAND_ERROR_MESSAGE = "[ERROR] %s 는 올바른 입력이 아닙니다. (y/n 중에 입력해주세요.)";
    private final String command;

    PlayerCommand(final String command) {
        this.command = command;
    }

    public static PlayerCommand from(final String command) {
        return Arrays.stream(PlayerCommand.values())
                .filter(element -> element.command.equals(command))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(String.format(COMMAND_ERROR_MESSAGE, command)));
    }

    public boolean isHit() {
        return this.equals(HIT);
    }
}
