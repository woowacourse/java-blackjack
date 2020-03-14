package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class CardDeck {
    private static final String NULL_ERR_MSG = "%s이(가) 없습니다.";
    private static final String DUPLICATE_ERROR_MSG = "중복되는 카드가 존재합니다.";
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
}
