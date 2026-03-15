package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final Cards cards;

    public Deck() {
        this.cards = createDeck();
    }

    public Card drawCard() {
        validateDeckSize();
        Card card = cards.removeFirst();
        return card;
    }

    private void validateDeckSize() {
        if (cards.getSize() == 0) {
            throw new IllegalArgumentException("덱에 카드가 없습니다.");
        }
    }

    private Cards createDeck() {
        List<Card> cards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            for (Number number : Number.values()) {
                cards.add(new Card(shape, number));
            }
        }
        Collections.shuffle(cards);
        return new Cards(cards);
    }
}
