package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {
    private static final int VALID_CARD_COUNTS = Symbol.values().length * Shape.values().length;
    private static final int DEFAULT_CARD_DRAW_COUNTS = 2;
    private static final String INVALID_CARD_COUNTS = "카드덱 생성에 필요한 카드의 숫자가 유효하지 않습니다.";
    private static final String CANNOT_DRAW_CARD = "더 이상 카드를 뽑을 수 없습니다.";

    private final Deque<Card> cardDeck;

    public CardDeck(List<Card> cards) {
        validateCardCounts(cards);
        Collections.shuffle(cards);
        this.cardDeck = new ArrayDeque<>(cards);
    }

    private void validateCardCounts(List<Card> cards) {
        int cardCounts = cards.size();
        if (cardCounts != VALID_CARD_COUNTS) {
            throw new IllegalArgumentException(INVALID_CARD_COUNTS);
        }
    }

    public Cards drawDefaultCards() {
        List<Card> cards = Stream.generate(this::draw)
                .limit(DEFAULT_CARD_DRAW_COUNTS)
                .collect(Collectors.toList());
        return new Cards(cards);
    }

    public Card draw() {
        if (cardDeck.isEmpty()) {
            throw new IllegalStateException(CANNOT_DRAW_CARD);
        }
        return cardDeck.pop();
    }

    int size() {
        return cardDeck.size();
    }
}
