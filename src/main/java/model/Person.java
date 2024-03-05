package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.card.Card;

public class Person {

    private List<Card> cards;

    public Person() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
