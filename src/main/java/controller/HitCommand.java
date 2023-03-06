package controller;

import java.util.Arrays;

public enum HitCommand {
    Y("y", true),
    N("n", false);

    private final String command;
    private final boolean isHit;

    HitCommand(String command, boolean isHit) {
        this.command = command;
        this.isHit = isHit;
    }

    public static HitCommand from(final String command) {
        return Arrays.stream(HitCommand.values())
            .filter(hitCommand -> command.equals(hitCommand.command))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isHit() {
        return isHit;
    }
}
