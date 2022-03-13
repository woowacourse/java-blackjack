package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    public Player(String name, List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean isDrawable() {
        return cards.calculateTotalScore() <= BLACKJACK_SCORE;
    }

    @Override
    public GameResult decideResult(int dealerScore) {
        if (getTotalScore() > BLACKJACK_SCORE) {
            return GameResult.LOSE;
        }

        if (dealerScore > BLACKJACK_SCORE) {
            return GameResult.WIN;
        }

        return GameResult.of(getTotalScore() - dealerScore);
    }
}
