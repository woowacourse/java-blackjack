package blackjack.domain.dealer;

import static java.util.stream.Collectors.toCollection;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Deck {
    private static final Set<Card> CARDS_CACHE = createCards();

    private final List<Card> cards;

    Deck(Set<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static Deck create() {
        return new Deck(CARDS_CACHE);
    }

    private static Set<Card> createCards() {
        final List<CardNumber> numbers = Arrays.asList(CardNumber.values());

        return Arrays.stream(CardShape.values())
                .flatMap(shape -> numbers.stream().map(number -> new Card(number, shape)))
                .collect(toCollection(LinkedHashSet::new));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card pick() {
        validateEmpty();

        Card card = cards.get(0);
        cards.remove(card);

        return card;
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
