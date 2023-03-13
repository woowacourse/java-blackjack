package blackjack.domain;

import static blackjack.domain.ExceptionMessage.HIT_COMMAND_NOT_FOUND;

import java.util.Arrays;

public enum HitCommand {

    YES("y"),
    NO("n");

    private final String keyword;

    HitCommand(final String keyword) {
        this.keyword = keyword;
    }

    public static HitCommand find(final String keyword) {
        return Arrays.stream(values())
                .filter(command -> command.keyword.equalsIgnoreCase(keyword))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(HIT_COMMAND_NOT_FOUND));
    }
}
