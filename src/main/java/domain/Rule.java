package domain;

import java.util.List;

public class Rule {

    public boolean isPlayerHitAllowed(List<TrumpCard> cards) {
        Score score = Score.from(cards);

        return !score.isHigherThan(Score.TWENTY)
                && score != Score.BUST;
    }

    public boolean isDealerHitAllowed(List<TrumpCard> cards) {

        Score score = Score.from(cards);

        return !score.isHigherThan(Score.SIXTEEN)
                && score != Score.BUST;
    }
}
