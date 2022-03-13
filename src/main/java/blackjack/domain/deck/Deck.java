package blackjack.domain.deck;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class Deck {

    private static final String NO_CARD_ERROR_MESSAGE = "덱이 더 이상 뽑을 수 있는 카드가 없습니다.";

    private final List<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = cards;
    }

    public static List<Card> initCards() {
        return Arrays.stream(Denomination.values())
            .flatMap(denomination -> Arrays.stream(Suit.values())
                .map(suit -> new Card(denomination, suit)))
            .collect(Collectors.toList());
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        try {
            return cards.remove(0);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(NO_CARD_ERROR_MESSAGE);
        }
    }
}
