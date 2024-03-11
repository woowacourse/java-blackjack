package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final List<Card> CASHED_CARDS;

    private final List<Card> cards;

    static {
        CASHED_CARDS = new ArrayList<>();
        for (final Denomination denomination : Denomination.values()) {
            Arrays.stream(Symbol.values()).map(symbol -> new Card(denomination, symbol)).forEach(CASHED_CARDS::add);
        }
    }

    public Deck() {
        cards = new ArrayList<>(CASHED_CARDS);
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱이 비어 있을 때 카드를 뽑을 수 없습니다.");
        }
        return pollLastCard();
    }

    private Card pollLastCard() {
        final Card card = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        return card;
    }
}
