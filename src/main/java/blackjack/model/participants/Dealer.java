package blackjack.model.participants;

import blackjack.model.cards.Cards;
import blackjack.model.cards.Score;
import blackjack.view.PlayerResultStatus;

public class Dealer extends Participant {
    private static final Score SCORE_STOP_DRAWING_CARDS = new Score(17);

    @Override
    public boolean checkCanGetMoreCard() {
        return SCORE_STOP_DRAWING_CARDS.isGreaterThan(cards.getCardsScore());
    }

    public PlayerResultStatus getResultStatus(Cards otherCards) {
        if (otherCards.isBusted()) {
            return PlayerResultStatus.LOSE;
        }
        if (cards.isBusted()) {
            return PlayerResultStatus.WIN;
        }
        return compareScore(otherCards);
    }

    private PlayerResultStatus compareScore(Cards otherCards) {
        Score dealerScore = cards.getCardsScore();
        Score otherScore = otherCards.getCardsScore();

        if (dealerScore.isGreaterThan(otherScore)) {
            return PlayerResultStatus.LOSE;
        }
        if (otherScore.isGreaterThan(dealerScore)) {
            return PlayerResultStatus.WIN;
        }
        return PlayerResultStatus.PUSH;
    }
}
