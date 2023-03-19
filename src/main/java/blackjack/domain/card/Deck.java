package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {

    private static final String DUPLICATE_ERROR_MESSAGE = "중복된 카드가 존재합니다.";
    private static final String NOT_EXIST_CARD_IN_DECK_ERROR_MESSAGE = "덱에 카드가 존재하지 않아 드로우를 할 수 없습니다.";

    public static final List<Card> TRUMP;

    private final Stack<Card> cards;

    static {
        TRUMP = Stream.of(Shape.values())
                .flatMap(shape -> Stream.of(Letter.values())
                        .map(letter -> Card.of(shape, letter)))
                .collect(Collectors.toUnmodifiableList());
    }

    protected Deck(final List<Card> cards) {
        validate(cards);

        this.cards = new Stack<>();
        this.cards.addAll(cards);
    }

    public static Deck create() {
        List<Card> shuffleTrump = new ArrayList<>(TRUMP);
        Collections.shuffle(shuffleTrump);

        return new Deck(shuffleTrump);
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

    public Card draw() {
        if (cards.empty()) {
            throw new IllegalArgumentException(NOT_EXIST_CARD_IN_DECK_ERROR_MESSAGE);
        }

        return cards.pop();
    }

    public int getCardCount() {
        return cards.size();
    }
}
