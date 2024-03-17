package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private static final List<Card> CACHED_CARDS = cacheCards();

    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Deck create() {
        return new Deck(CACHED_CARDS);
    }

    private static List<Card> cacheCards() {
        List<Card> cards = createOneDeck();

        Collections.shuffle(cards);

        return cards;
    }

    private static List<Card> createOneDeck() {
        return Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardNumber.values()).map(number -> Card.of(number, shape)))
                .collect(Collectors.toList());
    }

    public Card pick() {
        validateEmpty();

        return cards.remove(cards.size() - 1);
    }

    private void validateEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 부족합니다.");
        }
    }
}
