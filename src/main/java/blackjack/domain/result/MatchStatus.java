package blackjack.domain.result;

import java.util.Map;

public enum MatchStatus {

    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1),
    DRAW("무", 0),
    LOSS("패", -1);

    private final String name;
    private final double rate;

    MatchStatus(final String name, final double rate) {
        this.name = name;
        this.rate = rate;
    }

    public Long countMatchStatus(final Map<String, MatchStatus> matchStatuses) {
        return matchStatuses.values().stream()
                .filter(this::equals)
                .count();
    }

    public String getName() {
        return name;
    }

}
