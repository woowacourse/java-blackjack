package blackjack.model.participants;

import blackjack.model.blackjackgame.ResultStatus;

public class Dealer extends Participant {
    private static final int SCORE_STOP_DRAWING_CARDS = 16;

    @Override
    public boolean checkCanGetMoreCard() {
        return cards.getCardsScore() <= SCORE_STOP_DRAWING_CARDS;
    }

    public ResultStatus determineWinner(Player player) {
        return player.getResultStatus(cards);
    }
}
