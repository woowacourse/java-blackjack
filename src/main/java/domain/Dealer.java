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

    public GameResult judgeResultForUser(int userTotalScore) {
        // 유저 버스트
        if (userTotalScore > MAXIMUM_TOTAL_SCORE) {
            return GameResult.LOSE;
        }

        // 딜러와 유저 둘 다 블랙잭
        if ((userTotalScore == MAXIMUM_TOTAL_SCORE) && (hand.getHandTotalScore() == MAXIMUM_TOTAL_SCORE)) {
            return GameResult.DRAW;
        }

        // 유저만 블랙잭
        if (userTotalScore == MAXIMUM_TOTAL_SCORE) {
            return GameResult.BLACKJACK;
        }

        // 딜러만 블랙잭
        if (hand.getHandTotalScore() == MAXIMUM_TOTAL_SCORE) {
            return GameResult.LOSE_BY_BLACKJACK;
        }

        // 딜러 버스트
        if (hand.getHandTotalScore() > MAXIMUM_TOTAL_SCORE) {
            return GameResult.WIN;
        }

        // 둘 다 21 이하
        if (userTotalScore > hand.getHandTotalScore()) {
            return GameResult.WIN;
        }

        if (userTotalScore == hand.getHandTotalScore()) {
            return GameResult.DRAW;
        }

        return GameResult.LOSE;
    }
}
