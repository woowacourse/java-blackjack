package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import java.util.ArrayList;

public abstract class Participant {

    public static final int INITIAL_CARD_HAND = 2;
    public static final int NORMAL_CARD_HAND = 1;

    protected final Cards cards;
    private final Name name;

    public Participant(Name name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = name;
    }

    public abstract boolean isReceivable();

    public abstract int calculateBestScore();

    public void receive(CardDeck cardDeck, int count) {
        this.cards.concat(cardDeck.distribute(count));
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
