package blackjack.domain.gamer;

import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Gamer {

    private final Name name;
    protected final Cards cards;

    public Gamer(String name) {
        this.name = new Name(name);
        cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getValues());
    }

    public int getCardsNumberSum() {
       return cards.sum();
    }

    public boolean isOverThan(int number) {
        return getCardsNumberSum() > number;
    }

    public String getName() {
        return name.getValue();
    }
}
