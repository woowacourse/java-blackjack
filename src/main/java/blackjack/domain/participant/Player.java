package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Player extends Participant {

    private Player(Name name) {
        super(name);
    }

    public static Player nameOf(String rawName) {
        return new Player(new Name(rawName));
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
