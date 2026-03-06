package model;

import java.util.ArrayList;
import java.util.List;
import model.dto.Card;

public class Cards {
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> get() {
        return new ArrayList<>(cards);
    }
}
