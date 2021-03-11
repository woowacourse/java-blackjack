package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    private static final int LIMIT_SCORE = 17;

    public Dealer() {
    }

    public boolean isOverThenLimitScore() {
        return getScoreToInt() >= LIMIT_SCORE;
    }

    public List<Card> getInitCard() {
        return Collections.singletonList(getCards().get(0));
    }
}
