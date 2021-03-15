package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Dealer extends User {
    private static final int DEALER_REDRAW_STANDARD = 17;
    private static final Name DEALER_NAME = Name.of("딜러");

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card getFirstCard() {
        return this.cards.getFirstCard();
    }

    @Override
    public boolean canContinue() {
        return this.getScore() < DEALER_REDRAW_STANDARD;
    }
}
