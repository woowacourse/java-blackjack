package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    public boolean canNotHit() {
        return getTotalScore() > 16;
    }

    public void reverseAllExceptOne() {
        this.getCards().forEach(Card::reverseCard);
        this.getCards().get(0).openCard();
    }

    public void openAllCard() {
        this.getCards().forEach(Card::openCard);
    }
}
