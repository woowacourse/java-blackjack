package blackjack.domain.gamer;

import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Gamer {

    private final Name name;
    protected final Cards cards;

    public Gamer(String name) {
        this.name = new Name(name);
        cards = new Cards();
    }

    public Gamer(String name, Card... cards) {
        this.name = new Name(name);
        this.cards = new Cards(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int sumCardsNumber() {
        return cards.sum();
    }

    public abstract boolean isDrawable();

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getValues());
    }

    public String getName() {
        return name.getValue();
    }
}
