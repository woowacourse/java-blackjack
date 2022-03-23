package blackjack.domain.cards;

import blackjack.domain.cards.card.Card;
import blackjack.domain.cards.card.denomination.Denomination;
import blackjack.domain.cards.card.denomination.Suit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public final class CardDeck {
    public static final int POP_CARD_COUNT = 1;
    private static final String NO_CARD_EXCEPTION_MESSAGE = "덱에 남은 카드가 없습니다";

    private final Queue<Card> value;

    public CardDeck() {
        List<Card> cards = init();
        Collections.shuffle(cards);
        this.value = new ArrayDeque<>(cards);
    }

    private List<Card> init() {
        return Arrays.stream(Denomination.values())
                .flatMap(denomination ->
                        Arrays.stream(Suit.values())
                                .map(suit -> Card.of(denomination, suit))
                )
                .collect(Collectors.toList());
    }

    public Card pop() {
        validateSize(POP_CARD_COUNT);
        return value.poll();
    }

    private void validateSize(final int cardCount) {
        if (value.size() < cardCount) {
            throw new ArrayIndexOutOfBoundsException(NO_CARD_EXCEPTION_MESSAGE);
        }
    }

    public List<Card> popCards(int cardCount) {
        validateSize(cardCount);
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < cardCount; i++) {
            cards.add(pop());
        }
        return cards;
    }

    public int size() {
        return value.size();
    }
}
