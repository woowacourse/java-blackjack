package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;

public abstract class Gamer {

    protected final Cards cards;

    public Gamer() {
        this.cards = new Cards(new ArrayList<>());
    }

    public abstract boolean canHit();

    public void draw(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return cards.sum();
    }

    public Cards getCards() {
        return cards;
    }
}
