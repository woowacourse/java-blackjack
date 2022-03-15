package blackjack.domain.result;

import blackjack.domain.card.group.Cards;
import blackjack.util.Constants;

public final class Point {
    private static final int ACE_MINUS_NUMBER = 10;
    private static final int MIN_ACE_COUNT = 0;
    
    private final int value;
    
    private Point(Cards cards) {
        this.value = computeWithAce(cards.getRawPoint(), cards.getAceCount());
    }
    
    public static Point fromCards(Cards cards) {
        return new Point(cards);
    }
    
    public int get() {
        return value;
    }
    
    private static int computeWithAce(int point, int aceCount) {
        if (point > Constants.BLACKJACK_NUMBER && aceCount > MIN_ACE_COUNT) {
            point -= ACE_MINUS_NUMBER;
            return computeWithAce(point, --aceCount);
        }
        return point;
    }
}
