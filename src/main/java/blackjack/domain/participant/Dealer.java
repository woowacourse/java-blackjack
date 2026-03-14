package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Dealer extends Participant {

    private static final String DEALER_NICKNAME = "딜러";
    private static final int DEALER_SCORE = 16;

    public Dealer() {
        super(DEALER_NICKNAME);
    }

    public Card getFirstCard() {
        return getCards().getFirst();
    }

    @Override
    public boolean isDrawable() {
        return getScore() <= DEALER_SCORE;
    }
}
