package domain.Gamer;

import domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Gamer {
    private String name;
    private List<Card> cards;

    private void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return name + " : " + cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(","));
    }
}
