package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Dealer extends User {
    private static final String name = "딜러";
    public static final int LOWER_BOUND = 16;

    public Dealer() {
        super(name);
    }

    public Card getFirstCard() {
        return super.getCards().get(0);
    }

    @Override
    public boolean receivable() {
        super.getPoint().computePoint(super.getCards());
        if (!super.getPoint().isBiggerThan(new Point(LOWER_BOUND))) {
            return true;
        }
        return false;
    }
}
