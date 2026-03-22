package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = createDeck();
    }

    public Card drawCard() {
        validateDeckSize();
        return cards.removeFirst();
    }

    private void validateDeckSize() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱에 카드가 없습니다.");
        }
    }

    private List<Card> createDeck() {
        List<Card> cards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            for (Number number : Number.values()) {
                cards.add(new Card(shape, number));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }
}
