package domain.strategy;

import domain.TrumpCard;
import java.util.Deque;
import java.util.LinkedList;

public class TestDrawStrategy implements DrawStrategy {

    private final String EMPTY_DECK_ERROR = "덱이 비어있어 뽑을 수 없습니다.";
    private final String EMPTY_DRAW_CARD = "순서대로 뽑을 카드가 없습니다.";

    private final Deque<TrumpCard> drawOrder;

    public TestDrawStrategy(Deque<TrumpCard> drawOrder) {
        this.drawOrder = new LinkedList<>(drawOrder);
    }

    @Override
    public TrumpCard draw(Deque<TrumpCard> trumpCards) {
        validateDrawCard();
        validateEmptyDeck(trumpCards);
        TrumpCard testRemoveTrumpCard = drawOrder.removeFirst();
        trumpCards.remove(testRemoveTrumpCard);
        return testRemoveTrumpCard;
    }

    private void validateDrawCard() {
        if (drawOrder.isEmpty()) {
            throw new IllegalStateException(EMPTY_DRAW_CARD);
        }
    }

    private void validateEmptyDeck(Deque<TrumpCard> deckCards) {
        if (deckCards.isEmpty()) {
            throw new IllegalStateException(EMPTY_DECK_ERROR);
        }
    }
}
