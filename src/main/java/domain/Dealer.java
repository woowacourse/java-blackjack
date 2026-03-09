package domain;

import vo.GameResult;

public class Dealer {
    private static final Integer MAXIMUM_TOTAL_SCORE = 21;

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
        if (hand.getHandTotalScore() > MAXIMUM_TOTAL_SCORE) {
            return GameResult.LOSE;
        }

        if (hand.getHandTotalScore() == MAXIMUM_TOTAL_SCORE) {
            return GameResult.WIN;
        }

        if (userTotalScore > hand.getHandTotalScore()) {
            return GameResult.LOSE;
        }

        return GameResult.WIN;
    }

    public GameResult judgeUserWin(int userScore) {
        if (userScore > MAXIMUM_TOTAL_SCORE) {
            return GameResult.LOSE;
        }

        if (userScore == MAXIMUM_TOTAL_SCORE) {
            return GameResult.WIN;
        }

        if (hand.getHandTotalScore() > MAXIMUM_TOTAL_SCORE) {
            return GameResult.WIN;
        }

        if (userScore > hand.getHandTotalScore() && (hand.getHandTotalScore() < MAXIMUM_TOTAL_SCORE)) {
            return GameResult.WIN;
        }

        return GameResult.LOSE;
    }
}
