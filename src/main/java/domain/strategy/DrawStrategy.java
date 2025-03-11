package domain.strategy;

import domain.blackjackgame.TrumpCard;
import java.util.Deque;

public interface DrawStrategy {

    TrumpCard draw(Deque<TrumpCard> trumpCards);
}
