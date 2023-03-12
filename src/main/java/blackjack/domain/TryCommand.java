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

    public static boolean isHit(String inputCommand) {
        return Arrays.stream(values())
                .filter(tryCommand -> tryCommand.command.equals(inputCommand))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."))
                .getHit();
    }

    public boolean getHit() {
        return hit;
    }
}
