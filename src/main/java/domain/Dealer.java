package domain;

import vo.GameResult;

public class Dealer {
    private final static Integer WINNING_SCORE_BOUNDARY = 21;

    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.saveCard(card);
    }

    public String getCardsDisplay() {
        return hand.getCardsDisplay();
    }

    public void calculateScore() {
        hand.calculateHandScore();
    }

    public Boolean determineDealerDealMore() {
        return hand.determineDealerDealMore();
    }

    public String getDealerFinalDisplay() {
        return hand.getFinalDisplay();
    }

    public GameResult judgeUserResult(int userTotalScore) {
        if (hand.getTotalScore() > WINNING_SCORE_BOUNDARY) {
            return GameResult.LOSE;
        }

        if (hand.getTotalScore() == WINNING_SCORE_BOUNDARY) {
            return GameResult.WIN;
        }

        if (userTotalScore > hand.getTotalScore()) {
            return GameResult.LOSE;
        }

        return GameResult.WIN;
    }

    public GameResult judgeUserWin(int userScore) {
        if (userScore > WINNING_SCORE_BOUNDARY) {
            return GameResult.LOSE;
        }

        if (userScore == WINNING_SCORE_BOUNDARY) {
            return GameResult.WIN;
        }

        if (hand.getTotalScore() > WINNING_SCORE_BOUNDARY) {
            return GameResult.WIN;
        }

        if (userScore > hand.getTotalScore() && (hand.getTotalScore() < WINNING_SCORE_BOUNDARY)) {
            return GameResult.WIN;
        }

        return GameResult.LOSE;
    }
}
