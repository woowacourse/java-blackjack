package domain.card.strategy;

import domain.card.TrumpCard;
import java.util.Deque;
import java.util.LinkedList;

public class TestDrawStrategy implements DrawStrategy {

    private final String EMPTY_DRAW_CARD = "순서대로 뽑을 카드가 없습니다.";

    private final Deque<TrumpCard> drawOrder;

    public TestDrawStrategy(Deque<TrumpCard> drawOrder) {
        this.drawOrder = new LinkedList<>(drawOrder);
    }

    @Override
    public TrumpCard draw(Deque<TrumpCard> trumpCards) {
        validateDraw(trumpCards);
        validateDrawCard();
        TrumpCard testRemoveTrumpCard = drawOrder.removeFirst();
        trumpCards.remove(testRemoveTrumpCard);
        return testRemoveTrumpCard;
    }

    private void validateDrawCard() {
        if (drawOrder.isEmpty()) {
            throw new IllegalStateException(EMPTY_DRAW_CARD);
        }
    }
}
