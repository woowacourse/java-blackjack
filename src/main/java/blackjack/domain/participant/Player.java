package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Player extends Participant {

    public Player(Name name) {
        super(name);
    }

    @Override
    public boolean isReceivable() {
        return !isBusted();
    }

    @Override
    public List<Card> findShowingCards() {
        return getCards();
    }
}
