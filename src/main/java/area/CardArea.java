package area;

import card.Card;

import java.util.ArrayList;
import java.util.List;

public class CardArea {

    private final List<Card> cards = new ArrayList<>();

    public CardArea(final Card firstCard, final Card secondCard) {
        cards.addAll(List.of(firstCard, secondCard));
    }

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }
}
