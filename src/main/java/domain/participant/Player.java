package domain.participant;

import domain.PlayerGameResult;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public PlayerGameResult compareScore(int dealerScore) {
        if (isBust() && dealerScore > 21) {
            return PlayerGameResult.DRAW;
        }
        if (isBust() && dealerScore <= 21) {
            return PlayerGameResult.LOSE;
        }
        if (!isBust() && dealerScore > 21) {
            return PlayerGameResult.WIN;
        }
        return getGameResult(dealerScore);
    }

    private PlayerGameResult getGameResult(int dealerScore) {
        if (calculateScore() > dealerScore) {
            return PlayerGameResult.WIN;
        }
        if (calculateScore() == dealerScore) {
            return PlayerGameResult.DRAW;
        }
        return PlayerGameResult.LOSE;
    }
}
