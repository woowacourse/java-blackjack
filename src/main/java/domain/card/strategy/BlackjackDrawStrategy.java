package domain.card.strategy;

import domain.card.TrumpCard;
import java.util.Deque;

public class BlackjackDrawStrategy implements DrawStrategy {

    @Override
    public TrumpCard draw(Deque<TrumpCard> trumpCards) {
        validateDraw(trumpCards);
        return trumpCards.pop();
    }
}
