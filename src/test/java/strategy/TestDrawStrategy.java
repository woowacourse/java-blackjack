package strategy;

import domain.TrumpCard;
import domain.strategy.DrawStrategy;
import except.BlackJackException;
import java.util.Deque;
import java.util.LinkedList;

public class TestDrawStrategy implements DrawStrategy {

    private static final String INVALID_DRAW_STATE = "덱이 비어있어 뽑을 수 없습니다.";

    private final Deque<TrumpCard> testTrumpCards;

    public TestDrawStrategy(Deque<TrumpCard> testTrumpCards) {
        this.testTrumpCards = new LinkedList<>(testTrumpCards);
    }

    @Override
    public TrumpCard draw(Deque<TrumpCard> trumpCards) {
        validateDraw(testTrumpCards);
        validateDraw(trumpCards);
        TrumpCard testRemoveTrumpCard = testTrumpCards.removeFirst();
        trumpCards.remove(testRemoveTrumpCard);
        return testRemoveTrumpCard;
    }

    @Override
    public void validateDraw(Deque<TrumpCard> trumpCards) {
        if (trumpCards.isEmpty()) {
            throw new BlackJackException(INVALID_DRAW_STATE);
        }
    }
}
