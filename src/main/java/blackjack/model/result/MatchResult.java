package blackjack.model.result;

import blackjack.model.card.Score;

public enum MatchResult {
    WIN,
    LOSE,
    PUSH;

    public static MatchResult decideByScore(final Score target, final Score other) {
        if (target.equalTo(other)) {
            return PUSH;
        } else if (target.greaterThan(other)) {
            return WIN;
        }
        return LOSE;
    }
}
