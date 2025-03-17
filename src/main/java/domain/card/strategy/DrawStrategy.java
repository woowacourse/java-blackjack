package domain.card.strategy;

import domain.card.TrumpCard;
import java.util.Deque;

public interface DrawStrategy {
    String EMPTY_DECK_ERROR = "덱이 비어있어 뽑을 수 없습니다.";

    default void validateDraw(Deque<TrumpCard> deckCards) {
        if (deckCards.isEmpty()) {
            throw new IllegalStateException(EMPTY_DECK_ERROR);
        }
    }

    TrumpCard draw(Deque<TrumpCard> trumpCards);
}
