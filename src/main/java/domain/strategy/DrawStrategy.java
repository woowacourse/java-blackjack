package domain.strategy;

import domain.TrumpCard;
import java.util.Deque;

public interface DrawStrategy {

    TrumpCard draw(Deque<TrumpCard> trumpCards);


    void validateDraw(Deque<TrumpCard> trumpCards);
}
