package domain;

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
                .orElseThrow(() -> new IllegalArgumentException("y또는 n만 입력할 수 있습니다."));
    }

    public boolean isHit() {
        return isHit;
    }
}
