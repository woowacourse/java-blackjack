package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Deck {

    private static final String DECK_EMPTY_ERROR = "[ERROR] 카드를 모두 사용하였습니다.";

    private final List<Card> deck = new ArrayList<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            Stream.of(Denomination.values())
                .forEach(denomination -> deck.add(new Card(suit, denomination)));
        }
        Collections.shuffle(deck);
    }

    public Card pickCard() {
        validateDeckSize();
        return deck.remove(0);
    }

    private void validateDeckSize() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException(DECK_EMPTY_ERROR);
        }
    }

    public List<Card> getDeck() {
        return Collections.unmodifiableList(deck);
    }
}
