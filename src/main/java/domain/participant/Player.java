package domain.participant;

import domain.card.Cards;

public class Player extends Participant {

    public Player(final Name name, final Cards cards) {
        super(name, cards);
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }
}
