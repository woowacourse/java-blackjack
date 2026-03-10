package domain;

import vo.GameResult;

public class Dealer extends Participant{
    private final static Integer BLACKJACK_SCORE = 21;

    public Boolean determineDealerDealMore() {
        return hand.determineDealerDealMore();
    }

    public GameResult judgeDealerResult(int userTotalScore) {
        if (hand.calculateTotalScore() == BLACKJACK_SCORE || userTotalScore > BLACKJACK_SCORE) {
            return GameResult.WIN;
        }

        if (hand.calculateTotalScore() > BLACKJACK_SCORE || userTotalScore > hand.calculateTotalScore()) {
            return GameResult.LOSE;
        }

        return GameResult.WIN;
    }

    public GameResult judgeUserWin(int userScore) {
        if (userScore > BLACKJACK_SCORE) {
            return GameResult.LOSE;
        }

        int dealerScore = hand.calculateTotalScore();
        if (userScore == BLACKJACK_SCORE || dealerScore > BLACKJACK_SCORE || userScore >= dealerScore) {
            return GameResult.WIN;
        }

        return GameResult.LOSE;
    }

    public String getOneCardDisplay() {
        return hand.getFirstCardDisplay();
    }
}
