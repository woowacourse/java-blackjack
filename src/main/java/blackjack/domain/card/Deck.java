package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Deck {

    private static final String DECK_EMPTY_ERROR = "카드를 모두 사용하였습니다.";
    private static final int FIRST = 0;
    private static final int INITIAL_CARD_SIZE = 2;

    private final List<Card> deck = new ArrayList<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            Stream.of(Denomination.values())
                .forEach(denomination -> deck.add(new Card(suit, denomination)));
        }
        Collections.shuffle(deck);
    }

    public List<Card> pickInitialCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INITIAL_CARD_SIZE; i++) {
            cards.add(pickCard());
        }
        return cards;
    }

    public Card pickCard() {
        validateDeckSize();
        return deck.remove(FIRST);
    }

    private void validateDeckSize() {
        if (deck.isEmpty()) {
            throw new IndexOutOfBoundsException(DECK_EMPTY_ERROR);
        }
    }
}
