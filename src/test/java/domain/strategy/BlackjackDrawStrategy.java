package domain.strategy;

import domain.TrumpCard;
import except.BlackJackException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class BlackjackDrawStrategy implements DrawStrategy {

    private final String INVALID_DRAW_STATE = "덱이 비어있어 뽑을 수 없습니다.";

    @Override
    public TrumpCard draw(List<TrumpCard> trumpCards){
        validateDraw(trumpCards);
        return trumpCards.remove(0);
    }

    @Override
    public void validateDraw(List<TrumpCard> trumpCards){
        if(trumpCards.isEmpty()){
            throw new BlackJackException(INVALID_DRAW_STATE);
        }
    }
}
