package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.user.component.Name;

public class Dealer extends User {
    private static final String name = "딜러";
    public static final int LOWER_BOUND = 16;

    public Dealer() {
        super(new Name(name));
    }

    public Card getFirstCard() {
        return getCards().getCards().get(0);
    }

    @Override
    public boolean receivable() {
        if (getCards().computePoint() < LOWER_BOUND) {
            return true;
        }
        return false;
    }
}
