package domain.participant;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public class Dealer extends Participant {
    private Dealer(final Name name) {
        super(name);
    }

    private Dealer(final Name name, final Cards cards) {
        super(name, cards);
    }

    public static Dealer create() {
        return new Dealer(Name.of(DEALER_NAME));
    }

    public List<Card> getFirstCard() {
        return List.of(cards.getFirstCard());
    }
}
