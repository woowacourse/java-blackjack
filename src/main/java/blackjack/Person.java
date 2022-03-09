package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private List<Card> cards;

    public Person() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
