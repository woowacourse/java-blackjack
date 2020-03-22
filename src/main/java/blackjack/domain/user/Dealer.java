package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.user.component.Point;

public class Dealer extends User {
    private static final String name = "딜러";
    public static final int LOWER_BOUND = 16;

    public Dealer() {
        super(name);
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }

    @Override
    public boolean receivable() {
        Point point = new Point(cards);
        if (point.compareTo(new Point(LOWER_BOUND, false)) <= 0) {
            return true;
        }
        return false;
    }
}
