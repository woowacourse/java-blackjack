package domain;

import vo.GameResult;

public class Dealer {
    private Hand hand;

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
        if (hand.getHandTotalScore() > 21) {
            return GameResult.LOSE;
        }

        if (hand.getHandTotalScore() == 21) {
            return GameResult.WIN;
        }

        if (userTotalScore > hand.getHandTotalScore()) {
            return GameResult.LOSE;
        }

        return GameResult.WIN;
    }

    public GameResult judgeUserWin(int userScore) {
        if (userScore > 21) {
            return GameResult.LOSE;
        }

        if (userScore == 21) {
            return GameResult.WIN;
        }

        if (hand.getHandTotalScore() > 21) {
            return GameResult.WIN;
        }

        if (userScore > hand.getHandTotalScore() && (hand.getHandTotalScore() < 21)) {
            return GameResult.WIN;
        }

        return GameResult.LOSE;
    }
}
