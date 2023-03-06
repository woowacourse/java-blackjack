package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {

    public static final List<Card> CARDS;
    private static final String DUPLICATE_ERROR_MESSAGE = "중복된 카드가 존재합니다.";

    private final Stack<Card> cards;

    static {
        CARDS = Stream.of(Shape.values())
                .flatMap(shape -> Stream.of(Letter.values())
                        .map(letter -> new Card(shape, letter)))
                .collect(Collectors.toList());
    }

    public Deck() {
        this.cards = new Stack<>();

        Collections.shuffle(CARDS);
        this.cards.addAll(CARDS);
    }

    public Deck(final List<Card> cards) {
        this.cards = new Stack<>();
        validate(cards);

        this.cards.addAll(cards);
    }
    
    private void validate(final List<Card> cards) {
        validateDuplicateCard(cards);
    }

    private void validateDuplicateCard(final List<Card> cards) {
        if (isDuplicateCard(cards)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    private boolean isDuplicateCard(final List<Card> cards) {
        final Set<Card> uniqueCards = new HashSet<>(cards);
        return uniqueCards.size() != cards.size();
    }

    public Card drawCard() {
        return cards.pop();
    }

    public List<Card> getCards() {
        return cards;
    }
}
