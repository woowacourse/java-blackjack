package blackjack.domain.dealer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private static final List<Card> CACHED_CARDS = cacheCards();

    private final Deque<Card> cards;

    Deck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static Deck create() {
        return new Deck(CACHED_CARDS);
    }

    private static List<Card> cacheCards() {
        final List<Card> cards = createOneDeck();

        Collections.shuffle(cards);

        return cards;
    }

    private static List<Card> createOneDeck() {
        final List<CardNumber> numbers = Arrays.asList(CardNumber.values());

        return Arrays.stream(CardShape.values())
                .flatMap(shape -> numbers.stream().map(number -> Card.valueOf(number, shape)))
                .collect(Collectors.toList());
    }

    public Card pick() {
        validateEmpty();

        return cards.pop();
    }

    public List<Card> pick(final int count) {
        return Stream.generate(this::pick)
                .limit(count)
                .toList();
    }

    private void validateEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 부족합니다.");
        }
    }
}
