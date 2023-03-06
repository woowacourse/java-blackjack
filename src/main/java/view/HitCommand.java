package view;

import java.util.Arrays;

public enum HitCommand {
    YES("y", true),
    NO("n", false);

    private final String command;
    private final boolean isHit;

    HitCommand(String command, boolean isHit) {
        this.command = command;
        this.isHit = isHit;
    }

    public static boolean from(final String command) {
        return Arrays.stream(HitCommand.values())
            .filter(hitCommand -> command.equals(hitCommand.command))
            .findAny()
            .orElseThrow(IllegalArgumentException::new)
            .isHit;
    }
}
