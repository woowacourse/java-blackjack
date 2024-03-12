package blackjack.model.participants;

import blackjack.model.cards.Score;

public class Dealer extends Participant {
    private static final Score SCORE_STOP_DRAWING_CARDS = new Score(17);

    @Override
    public boolean checkCanGetMoreCard() {
        return SCORE_STOP_DRAWING_CARDS.isGreaterThan(cards.getCardsScore());
    }
}
