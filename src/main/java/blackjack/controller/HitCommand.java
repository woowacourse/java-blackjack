package blackjack.controller;

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
                .orElseThrow(() -> new IllegalArgumentException("키워드에 해당하는 명령어가 존재하지 않습니다."));
    }
}
