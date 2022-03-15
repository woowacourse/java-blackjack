package blackjack.domain.human;

import blackjack.domain.card.group.Cards;
import blackjack.domain.result.Point;

public final class Dealer extends Human {
    private static final int HIT_STANDARD_NUMBER = 16;
    
    private Dealer() {
        super(new Cards(), "딜러");
    }
    
    public static Dealer newInstance() {
        return new Dealer();
    }
    
    public boolean isAbleToHit() {
        return Point.fromCards(cards).get() <= HIT_STANDARD_NUMBER;
    }
}
