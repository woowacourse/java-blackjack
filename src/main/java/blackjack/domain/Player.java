package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

import java.util.List;

public class Player extends Participant {
    private static final int INITIAL_OPEN_CARD_COUNT = 2;
    protected Player(final ParticipantCards cards, final String name) {
        super(cards, name);
    }

    @Override
    public List<Card> initialOpen() {
        return cards.open(INITIAL_OPEN_CARD_COUNT);
    }

    @Override
    protected boolean isHittable() {
        return !cards.isBlackJack() && !cards.isBust();
    }
}
