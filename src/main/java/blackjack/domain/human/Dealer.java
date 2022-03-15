package blackjack.domain.human;

import blackjack.domain.result.Point;
import blackjack.domain.card.group.Cards;

public final class Dealer extends Human {
    private static final int HIT_STANDARD_NUMBER = 16;
    
    private Dealer() {
        super(new Cards(), "딜러");
    }
    
    public static Dealer newInstance() {
        return new Dealer();
    }
    
    @Override
    public boolean isAbleToHit() {
        return Point.fromCards(cards).get() <= HIT_STANDARD_NUMBER;
    }
}
