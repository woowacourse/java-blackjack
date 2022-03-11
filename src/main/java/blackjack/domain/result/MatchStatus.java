package blackjack.domain.result;

import java.util.Arrays;

public enum MatchStatus {

    WIN("승", true),
    LOSS("패", false);

    private final String name;
    private final boolean result;

    MatchStatus(final String name, final boolean result) {
        this.name = name;
        this.result = result;
    }

    public static MatchStatus valueOf(final boolean result) {
        return Arrays.stream(MatchStatus.values())
                .filter(it -> it.result == result)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("승패를 구분지을 수 없습니다."));
    }

    public String getName() {
        return name;
    }
}
