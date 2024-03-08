package blackjack.model.participants;

import blackjack.model.blackjackgame.ResultStatus;

public class Dealer extends Participant {
    private static final int STANDARD = 16;

    @Override
    public boolean checkDrawCardState() {
        return cards.getCardsScore() <= STANDARD;
    }

    public ResultStatus determineWinner(Player player) {
        return player.getResultStatus(cards);
    }
}
