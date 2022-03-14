package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.result.GameResult;
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
    public GameResult decideResult(Participant participant) {
        if (isBust()) {
            return GameResult.LOSE;
        }

        if (participant.isBust()) {
            return GameResult.WIN;
        }

        return GameResult.of(getTotalScore(), participant.getTotalScore());
    }
}
