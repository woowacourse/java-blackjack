package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Dealer extends Participant {

    private static final int MAX_SCORE_TO_RECEIVE_CARD = 16;

    public Dealer(Cards cards) {
        super("딜러", cards);
    }

    public boolean isRangeScoreToReceive() {
        return calculateScore() <= MAX_SCORE_TO_RECEIVE_CARD;
    }

    public Card openFirstCards() {
        return getCards().get(0);
    }
}
