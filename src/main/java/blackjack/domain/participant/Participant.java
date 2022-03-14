package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import java.util.ArrayList;

public abstract class Participant {

    public static final int INITIAL_CARD_COUNT = 2;
    public static final int NORMAL_CARD_COUNT = 1;

    protected final Cards cards;
    private final Name name;

    protected Participant(Name name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = name;
    }

    public abstract boolean isReceivable();

    public int calculateBestScore() {
        return cards.getBestScore();
    }

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
