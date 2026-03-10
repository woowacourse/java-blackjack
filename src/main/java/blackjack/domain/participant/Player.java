package blackjack.domain.participant;

import blackjack.domain.GameResult;

public class Player extends Participant {

    private boolean stopDrawing;

    public Player(String nickname) {
        super(nickname);
        stopDrawing = false;
    }

    @Override
    public boolean isDrawable() {
        if (stopDrawing) {
            return false;
        }
        if (isBusted()) {
            return false;
        }
        return !hand.isBlackjack();
    }

    public void stopDrawing() {
        stopDrawing = true;
    }

    public GameResult determinePlayerResult(Dealer dealer) {
        return determineGameResult(dealer);
    }

    private GameResult determineGameResult(Dealer dealer) {
        if (isBusted()) {
            return GameResult.LOSE;
        }
        if (dealer.isBusted()) {
            return GameResult.WIN;
        }
        return compareScore(dealer.getTotalScore(), getTotalScore());
    }

    private GameResult compareScore(int dealerScore, int playerScore) {
        if (dealerScore == playerScore) {
            return GameResult.DRAW;
        }
        if (dealerScore > playerScore) {
            return GameResult.LOSE;
        }
        return GameResult.WIN;
    }
}
