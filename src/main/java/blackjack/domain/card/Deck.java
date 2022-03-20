package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private static final String NO_CARD_ERROR_MESSAGE = "덱이 더 이상 뽑을 수 있는 카드가 없습니다.";

    private final List<Card> cards;

    private Deck(final List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static Deck create() {
        List<Card> cards = initCards();
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static List<Card> initCards() {
        return Arrays.stream(Denomination.values())
            .flatMap(denomination -> Arrays.stream(Suit.values())
                .map(suit -> new Card(denomination, suit)))
            .collect(Collectors.toList());
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(NO_CARD_ERROR_MESSAGE);
        }
        return cards.remove(0);
    }
}
