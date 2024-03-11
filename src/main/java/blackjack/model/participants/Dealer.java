package blackjack.model.participants;

import blackjack.model.cards.Cards;
import blackjack.view.PlayerResultStatus;

public class Dealer extends Participant {
    private static final int SCORE_STOP_DRAWING_CARDS = 16;

    @Override
    public boolean checkCanGetMoreCard() {
        return cards.getCardsScore() <= SCORE_STOP_DRAWING_CARDS;
    }

    public PlayerResultStatus getResultStatus(Cards otherCards) {
        if (otherCards.isGreaterThanWinningScore()) {
            return PlayerResultStatus.WIN;
        }
        if (cards.isGreaterThanWinningScore()) {
            return PlayerResultStatus.LOSE;
        }
        return compareScore(otherCards);
    }

    private PlayerResultStatus compareScore(Cards otherCards) {
        int calculatedScore = cards.getCardsScore();
        int otherScore = otherCards.getCardsScore();

        if (calculatedScore > otherScore) {
            return PlayerResultStatus.LOSE;
        }
        if (calculatedScore < otherScore) {
            return PlayerResultStatus.WIN;
        }
        return PlayerResultStatus.PUSH;
    }
}
