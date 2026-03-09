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

    public Boolean determineDealerDealMore() {
        return hand.determineDealerDealMore();
    }

    public String getDealerFinalDisplay() {
        return hand.getFinalDisplay();
    }

    public GameResult judgeUserResult(int userTotalScore) {
        if (hand.calculateTotalScore() == WINNING_SCORE_BOUNDARY) {
            return GameResult.WIN;
        }

        if (hand.calculateTotalScore() > WINNING_SCORE_BOUNDARY || userTotalScore > hand.calculateTotalScore()) {
            return GameResult.LOSE;
        }

        return GameResult.WIN;
    }

    public GameResult judgeUserWin(int userScore) {
        if (userScore > WINNING_SCORE_BOUNDARY) {
            return GameResult.LOSE;
        }

        int dealerScore = hand.calculateTotalScore();
        if (userScore == WINNING_SCORE_BOUNDARY || dealerScore > WINNING_SCORE_BOUNDARY || userScore > dealerScore) {
            return GameResult.WIN;
        }

        return GameResult.LOSE;
    }
}
