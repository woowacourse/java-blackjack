package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DRAWABLE_LIMIT_VALUE = 16;

    public Dealer(List<Card> cards) {
        super(DEALER_NAME, cards);
    }

    @Override
    public boolean isDrawable() {
        return cards.calculateTotalScore() <= DRAWABLE_LIMIT_VALUE;
    }

    @Override
    public GameResult decideResult(Participant participant) {
        if (participant.isBust()) {
            return GameResult.WIN;
        }

        if (isBust()) {
            return GameResult.LOSE;
        }

        return GameResult.of(getTotalScore(), participant.getTotalScore());
    }
}
