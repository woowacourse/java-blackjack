package blackjack.domain.participant;

import blackjack.domain.card.Deck;

public class Player extends Participant {
    public Player(final Deck deck, final String name) {
        super(deck, name);
    }

    @Override
    public boolean isHittable() {
        return !cards.isBlackJack() && !cards.isBust();
    }
}
