package blackjack.domain.rule;

public class Judge {

    private static final int BLACK_JACK = 21;

    public boolean isPlayerWin(Score dealerScore, Score playerScore) {
        if (playerScore.isAbove(BLACK_JACK)) {
            return false;
        }
        if (dealerScore.isAbove(BLACK_JACK)) {
            return true;
        }
        return playerScore.isBiggerThan(dealerScore);
    }
}
