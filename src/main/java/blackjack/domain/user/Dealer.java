package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Dealer extends Participant {
    public static final Name DEALER_NAME = Name.from("딜러");

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }
}
