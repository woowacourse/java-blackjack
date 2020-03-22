package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.user.component.Name;
import blackjack.domain.user.component.Point;

public class Dealer extends User {
    private static final String name = "딜러";
    public static final int LOWER_BOUND = 16;

    public Dealer() {
        super(new Name(name));
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }

    @Override
    public boolean receivable() {
        Point point = createPoint();
        if (point.isSmallerThan(new Point(LOWER_BOUND, false))) {
            return true;
        }
        return false;
    }
}
