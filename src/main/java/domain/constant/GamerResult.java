package domain.constant;

import domain.dto.HandStatus;

public enum GamerResult {
    WIN(1),
    DRAW(0),
    LOSE(-1),
    BLACKJACK(1.5);

    private static final int BUST_THRESHOLD = 21;
    private static final int BLACKJACK_SCORE = 21;
    private static final int INITIAL_HAND_COUNT = 2;
    private final double profitRatio;

    GamerResult(double profitRatio) {
        this.profitRatio = profitRatio;
    }

    public static GamerResult judgePlayerResult(HandStatus dealerHandStatus, HandStatus playerHandStatus) {
        if (isPlayerLose(dealerHandStatus.score(), playerHandStatus.score())) {
            return GamerResult.LOSE;
        }
        if (isBlackJack(playerHandStatus) && !isBlackJack(dealerHandStatus)) {
            return GamerResult.BLACKJACK;
        }
        if (isPlayerWin(dealerHandStatus.score(), playerHandStatus.score())) {
            return GamerResult.WIN;
        }
        return GamerResult.DRAW;
    }

    private static boolean isPlayerLose(int dealerScore, int playerScore) {
        return playerScore > BUST_THRESHOLD || (dealerScore <= BUST_THRESHOLD && dealerScore > playerScore);
    }

    private static boolean isPlayerWin(int dealerScore, int playerScore) {
        return dealerScore > BUST_THRESHOLD || dealerScore < playerScore;
    }

    private static boolean isBlackJack(HandStatus handStatus) {
        return handStatus.score() == BLACKJACK_SCORE
                && handStatus.handCount() == INITIAL_HAND_COUNT;
    }

    public GamerResult getOpponentGameResult() {
        if (this.equals(GamerResult.WIN) || this.equals(GamerResult.BLACKJACK)) {
            return GamerResult.LOSE;
        }
        if (this.equals(GamerResult.LOSE)) {
            return GamerResult.WIN;
        }
        return GamerResult.DRAW;
    }

    public double getProfitRatio() {
        return profitRatio;
    }
}
