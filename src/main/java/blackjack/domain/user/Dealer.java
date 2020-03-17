package blackjack.domain.user;

import blackjack.domain.card.Card;

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
        Point point = new Point(getCards());
        if (point.compareTo(new Point(LOWER_BOUND)) == -1) {
            return true;
        }
        return false;
    }
}
