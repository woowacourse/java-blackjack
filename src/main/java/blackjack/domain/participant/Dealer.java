package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Dealer extends Participant {

    private static final int CARD_DRAW_POINT = 16;

    @Override
    public boolean isDrawable() {
        final int currentScore = currentScore();
        return currentScore <= CARD_DRAW_POINT;
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }
}
