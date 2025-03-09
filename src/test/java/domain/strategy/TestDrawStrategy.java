package domain.strategy;

import domain.TrumpCard;
import except.BlackJackException;
import java.util.Deque;
import java.util.LinkedList;

public class TestDrawStrategy implements DrawStrategy {

    private final String EMPTY_DECK_ERROR = "덱이 비어있어 뽑을 수 없습니다.";
    private final String EMPTY_DRAW_CARD = "순서대로 뽑을 카드가 없습니다.";
    
    private final Deque<TrumpCard> testTrumpCards;

    public TestDrawStrategy(Deque<TrumpCard> testTrumpCards) {
        this.testTrumpCards = new LinkedList<>(testTrumpCards);
    }

    @Override
    public TrumpCard draw(Deque<TrumpCard> trumpCards) {
        validateDrawCard(testTrumpCards);
        validateEmptyDeck(trumpCards);
        TrumpCard testRemoveTrumpCard = testTrumpCards.removeFirst();
        trumpCards.remove(testRemoveTrumpCard);
        return testRemoveTrumpCard;
    }

    private void validateDrawCard(Deque<TrumpCard> trumpCards){
        if(trumpCards.isEmpty()){
            throw new IllegalStateException(EMPTY_DRAW_CARD);
        }
    }

    private void validateEmptyDeck(Deque<TrumpCard> trumpCards) {
        if (trumpCards.isEmpty()) {
            throw new BlackJackException(EMPTY_DECK_ERROR);
        }
    }
}
