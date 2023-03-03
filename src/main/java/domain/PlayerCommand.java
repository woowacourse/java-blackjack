package domain;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum PlayerCommand {

    HIT("y"),
    STAND("n");

    private final String command;

    PlayerCommand(final String command) {
        this.command = command;
    }

    public static PlayerCommand from(final String command) {
        return Arrays.stream(PlayerCommand.values())
                .filter(element -> element.command.equals(command))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public boolean isHit() {
        return this.equals(HIT);
    }

    public boolean isStand() {
        return this.equals(STAND);
    }
}
