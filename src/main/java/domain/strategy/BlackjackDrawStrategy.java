package domain.strategy;

import domain.TrumpCard;
import java.util.Deque;

public class BlackjackDrawStrategy implements DrawStrategy {

    private final String EMPTY_DECK_ERROR = "덱이 비어있어 뽑을 수 없습니다.";

    @Override
    public TrumpCard draw(Deque<TrumpCard> trumpCards) {
        validateDraw(trumpCards);
        return trumpCards.pop();
    }

    private void validateDraw(Deque<TrumpCard> deckCards) {
        if (deckCards.isEmpty()) {
            throw new IllegalStateException(EMPTY_DECK_ERROR);
        }
    }
}
