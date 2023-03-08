package domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class HandCards {

    private final List<Card> cards;

    public HandCards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Integer> getValues() {
        return cards.stream()
                .map(Card::getValue)
                .collect(Collectors.toList());
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
