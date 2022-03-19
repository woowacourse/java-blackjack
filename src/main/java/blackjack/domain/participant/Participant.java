package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import java.util.ArrayList;

public abstract class Participant {

    protected final Cards cards;
    private final Name name;

    protected Participant(Name name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = name;
    }

    public abstract boolean isReceivable();

    public void receive(Cards cards) {
        this.cards.concat(cards);
    }

    public int calculateBestScore() {
        return cards.getBestPossible();
    }

    protected boolean isBusted() {
        return cards.isBusted();
    }

    protected boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
