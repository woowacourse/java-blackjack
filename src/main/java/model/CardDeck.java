package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateHand() {
        return cards.stream()
                .map(Card::getNumberValue)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public boolean isBust(){
        return calculateHand() > 21;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
