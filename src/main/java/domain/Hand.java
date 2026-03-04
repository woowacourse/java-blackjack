package domain;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int calculate() {
        return cards.stream()
                .mapToInt(Card::getCardValue)
                .sum();
    }
}
