package domain;

import java.util.List;

public class Rule {

    public boolean isPlayerHitAllowed(List<TrumpCard> cards) {
        Score score = Score.from(cards);

        return score != Score.BUST && score != Score.BLACKJACK;
    }

    public boolean isDealerHitAllowed(List<TrumpCard> cards) {
        Score score = Score.from(cards);

        return score != Score.BUST
                && score != Score.BLACKJACK
                && score != Score.SEVENTEEN
                && score != Score.EIGHTEEN
                && score != Score.NINETEEN
                && score != Score.TWENTY
                && score != Score.TWENTY_ONE;
    }
}
