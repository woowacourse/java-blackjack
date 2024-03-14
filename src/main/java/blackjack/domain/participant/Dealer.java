package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HandGenerator;

import java.util.List;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final int INITIAL_OPENED_CARD_COUNT = 1;
    private static final int HIT_THRESHOLD = 16;

    public Dealer(HandGenerator handGenerator) {
        super(new Name(DEALER_NAME), handGenerator);
    }

    @Override
    public List<Card> getInitialOpenedCards() {
        return getCardsByCount(INITIAL_OPENED_CARD_COUNT);
    }

    @Override
    public boolean canHit() {
        return !isTotalScoreGreaterThan(HIT_THRESHOLD);
    }

    public int hitAndCountCards(Deck deck) {
        int hitCardCount = 0;
        while (canHit()) {
            hitCardCount++;
            addCard(deck);
        }
        return hitCardCount;
    }
}
