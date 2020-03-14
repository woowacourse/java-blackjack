package blackjack.domain.card;

import blackjack.domain.card.component.CardFigure;
import blackjack.domain.card.component.CardNumber;

import java.util.*;
import java.util.stream.Collectors;

public class CardDeck {
    private static final String NULL_ERR_MSG = "%s이(가) 없습니다.";
    private static final String DUPLICATE_ERROR_MSG = "중복되는 카드가 존재합니다.";
    private static final String NO_CARD_ERROR_MSG = "카드를 찾을 수 없습니다.";
    private static final String CARD_EMPTY_ERROR_MSG = "카드를 모두 사용했습니다.";
    public static final int FIRST_INDEX = 1;
    private final Queue<Card> cards;

    public CardDeck(List<Card> input) {
        Objects.requireNonNull(input, String.format(NULL_ERR_MSG, "카드"));
        cards = new LinkedList<>(input);
        validateNotDuplicate(cards);
    }

    private void validateNotDuplicate(Queue<Card> cards) {
        List<Card> distinctCards = cards.stream()
                .distinct()
                .collect(Collectors.toList());
        if (distinctCards.size() != cards.size()) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MSG);
        }
    }

    public int getSize() {
        return cards.size();
    }

    public Card getCard() {
        return cards.poll();
    }

    public Card getCard(CardNumber number, CardFigure cardFigure) {
        return cards.stream()
                .filter(card -> card.has(number, cardFigure))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_CARD_ERROR_MSG));
    }
}
