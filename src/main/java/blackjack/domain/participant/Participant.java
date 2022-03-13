package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import java.util.ArrayList;

public abstract class Participant {

    public static final int ACE_ADDITIONAL_NUMBER = 10;
    public static final int BUST_THRESHOLD = 21;

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
