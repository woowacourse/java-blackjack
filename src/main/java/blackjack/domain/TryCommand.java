package blackjack.domain;

import java.util.Arrays;

public enum TryCommand {
    HIT("y", true),
    STAND("n", false);

    private final String command;
    private final boolean hit;

    TryCommand(String command, boolean hit) {
        this.command = command;
        this.hit = hit;
    }

    public static boolean hit(String input) {
        return Arrays.stream(values())
                .filter(tryCommand -> tryCommand.command.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."))
                .hit();
    }

    public boolean hit() {
        return hit;
    }
}
