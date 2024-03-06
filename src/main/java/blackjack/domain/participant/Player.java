package blackjack.domain.participant;

import blackjack.domain.Card;
import java.util.List;

public class Player extends Participant {

    public Player(List<Card> cards) {
        super(cards);
    }

    @Override
    protected int getMaxDrawableScore() {
        return BLACKJACK_SCORE;
    }
}
