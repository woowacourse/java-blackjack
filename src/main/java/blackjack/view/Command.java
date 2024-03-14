package blackjack.view;

import java.util.Arrays;

public enum Command {
    HIT("y"),
    STAND("n");

    private final String identifier;

    Command(String identifier) {
        this.identifier = identifier;
    }

    public static Command from(String identifier) {
        return Arrays.stream(Command.values())
                .filter(command -> identifier.equals(command.identifier))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        HIT.identifier + "또는 " + STAND.identifier + "만 입력 가능합니다."
                ));
    }

    public boolean isHit() {
        return this == HIT;
    }

    public String getIdentifier() {
        return identifier;
    }
}
