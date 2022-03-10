package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import java.util.ArrayList;

public abstract class Participant {

    public static final int INITIAL_CARD_HAND = 2;

    protected final Cards cards;
    private final Name name;

    public Participant(Name name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = name;
    }

    public abstract boolean isReceivable();

    public abstract int calculateBestScore();

    public void receive(Cards cards) {
        this.cards.concat(cards);
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
