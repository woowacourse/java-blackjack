package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> cards;

    public CardDeck() {
        this.cards = initializeCards();
    }

    private List<Card> initializeCards() {
        List<Card> cards = new ArrayList<>();

        for (Denomination denomination : Denomination.values()) {
            addCards(cards, denomination);
        }

        return cards;
    }

    private void addCards(List<Card> cards, Denomination denomination) {
        for (Emblem emblem : Emblem.values()) {
            cards.add(new Card(denomination, emblem));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
