package domain.card;

import static java.util.stream.Collectors.toList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

public class CardDeck {
    private static final int INITIAL_SIZE = 52;
    private static final String CAN_NOT_SHUFFLE_USED_DECK = "사용중인 카드는 다시 섞을 수 없습니다.";
    private static final List<Card> originalCards;

    private final Deque<Card> cards;

    static {
        originalCards = List.copyOf(
                Arrays.stream(Suit.values())
                        .flatMap(CardDeck::getCardStream)
                        .collect(toList())
        );
    }

    private static Stream<Card> getCardStream(Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> Card.of(suit, denomination));
    }

    private CardDeck() {
        this.cards = new ArrayDeque<>(originalCards);
    }

    public static CardDeck newInstance() {
        return new CardDeck();
    }

    public Card getCard() {
        return cards.pop();
    }

    public CardDeck shuffle() {
        if (cards.size() != INITIAL_SIZE) {
            throw new IllegalStateException(CAN_NOT_SHUFFLE_USED_DECK);
        }

        cards.clear();
        List<Card> newCards = new ArrayList<>(originalCards);
        Collections.shuffle(newCards);
        cards.addAll(newCards);
        return this;
    }
}
