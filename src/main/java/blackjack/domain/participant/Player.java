package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;

public class Player extends Participant {
    private static final int INITIAL_OPENED_CARD_COUNT = 2;
    private static final int HIT_THRESHOLD = 20;

    public Player(Name name, Deck deck) {
        super(name, deck);
    }

    @Override
    public List<Card> getInitialOpenedCards() {
        return getCardsByCount(INITIAL_OPENED_CARD_COUNT);
    }

    @Override
    public boolean canHit() {
        return !isBust() && !isTotalScoreGreaterThan(HIT_THRESHOLD);
    }
}
