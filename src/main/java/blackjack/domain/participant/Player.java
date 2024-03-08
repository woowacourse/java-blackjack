package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.HandGenerator;

import java.util.List;

public class Player extends Participant {
    private static final int INITIAL_OPENED_CARD_COUNT = 2;

    public Player(Name name, HandGenerator handGenerator) {
        super(name, handGenerator);
    }

    @Override
    public List<Card> getInitialOpenedCards() {
        return getCardsByCount(INITIAL_OPENED_CARD_COUNT);
    }

    @Override
    public boolean canHit() {
        return !isBust();
    }
}
