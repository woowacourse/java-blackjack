package domain;

import vo.GameResult;
import vo.Money;

public class Dealer {
    private static final Integer MAXIMUM_TOTAL_SCORE = 21;

    private final Hand hand;
    private Money profit;

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

    public GameResult judgeResultForUser(int userTotalScore) {
        if (userTotalScore > MAXIMUM_TOTAL_SCORE) {
            return GameResult.LOSE;
        }

        if (userTotalScore == MAXIMUM_TOTAL_SCORE) {
            return GameResult.BLACKJACK;
        }

        // 딜러의 점수가 21을 초과하는 경우, 유저 승
        if (hand.getHandTotalScore() > MAXIMUM_TOTAL_SCORE) {
            return GameResult.WIN;
        }

        // 딜러의 점수가 21보다 작으면서, 유저의 점수가 딜러의 점수보다 큰 경우, 유저 승
        if (userTotalScore > hand.getHandTotalScore() && (hand.getHandTotalScore() < MAXIMUM_TOTAL_SCORE)) {
            return GameResult.WIN;
        }

        if (userTotalScore == hand.getHandTotalScore()) {
            return GameResult.DRAW;
        }

        return GameResult.LOSE;
    }

    public void updateProfit(Money profit) {
        this.profit = profit;
    }
}
