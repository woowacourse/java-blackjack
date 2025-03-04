package domain.strategy;

import domain.TrumpCard;
import except.BlackJackException;
import java.util.ArrayList;
import java.util.List;

public class TestDrawStrategy implements DrawStrategy {

    private final String INVALID_DRAW_STATE = "덱이 비어있어 뽑을 수 없습니다.";
    private final List<TrumpCard> testTrumpCards;

    public TestDrawStrategy(List<TrumpCard> testTrumpCards){
        this.testTrumpCards = new ArrayList<>(testTrumpCards);
    }

    @Override
    public TrumpCard draw(List<TrumpCard> trumpCards){
        validateDraw(testTrumpCards);
        validateDraw(trumpCards);
        TrumpCard testRemoveTrumpCard = testTrumpCards.get(0);
        testTrumpCards.remove(0);
        trumpCards.remove(testRemoveTrumpCard);
        return testRemoveTrumpCard;
    }

    @Override
    public void validateDraw(List<TrumpCard> trumpCards){
        if(trumpCards.isEmpty()){
            throw new BlackJackException(INVALID_DRAW_STATE);
        }
    }
}
