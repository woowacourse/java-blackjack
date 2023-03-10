package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Deck {
    private static final String ERROR_EMPTY_CARDS = "[ERROR] 뽑을 수 있는 카드가 존재하지 않습니다";
    private static final List<Card> CARDS;

    static {
        CARDS = Arrays.stream(Denomination.values())
                .flatMap(denomination -> Arrays.stream(Suit.values())
                        .map(suit -> new Card(denomination, suit)))
                .collect(Collectors.toUnmodifiableList());
    }

    private final Queue<Card> cards;

    private Deck(ArrayDeque<Card> cards) {
        this.cards = cards;
    }

    public static Deck create() {
        List<Card> initialCards = new ArrayList<>(CARDS);
        Collections.shuffle(initialCards);

        return new Deck(new ArrayDeque<>(initialCards));
    }

    public Card pollAvailableCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(ERROR_EMPTY_CARDS);
        }

        return cards.poll();
    }

    public List<Card> pollTwoCards() {
        return List.of(pollAvailableCard(), pollAvailableCard());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(new ArrayList<>(cards));
    }
}
