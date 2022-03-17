package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DRAWABLE_LIMIT_VALUE = 16;

    public Dealer(List<Card> cards) {
        super(DEALER_NAME, cards);
    }

    @Override
    public boolean canHit() {
        return getTotalScore() <= DRAWABLE_LIMIT_VALUE;
    }

    @Override
    public boolean isWin(Participant player) {
        return getTotalScore() > player.getTotalScore();
    }

    @Override
    public boolean isSameScore(Participant player) {
        return getTotalScore() == player.getTotalScore();
    }
}
