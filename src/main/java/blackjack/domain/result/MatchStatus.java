package blackjack.domain.result;

import java.util.Map;

public enum MatchStatus {

    BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSS(-1);

    private final double rate;

    MatchStatus(final double rate) {
        this.rate = rate;
    }

    public int multiplyRate(final int number) {
        return (int) Math.floor(this.rate * number);
    }

    public Long countMatchStatus(final Map<String, MatchStatus> matchStatuses) {
        return matchStatuses.values().stream()
                .filter(this::equals)
                .count();
    }

}
