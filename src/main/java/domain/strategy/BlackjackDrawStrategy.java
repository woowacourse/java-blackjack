package domain.strategy;

import domain.TrumpCard;
import except.BlackJackException;
import java.util.Deque;

public class BlackjackDrawStrategy implements DrawStrategy {

    private final String INVALID_DRAW_STATE = "덱이 비어있어 뽑을 수 없습니다.";

    @Override
    public TrumpCard draw(Deque<TrumpCard> trumpCards) {
        validateDraw(trumpCards);
        return trumpCards.pop();
    }

    @Override
    public void validateDraw(Deque<TrumpCard> trumpCards) {
        if (trumpCards.isEmpty()) {
            throw new BlackJackException(INVALID_DRAW_STATE);
        }
    }
}
