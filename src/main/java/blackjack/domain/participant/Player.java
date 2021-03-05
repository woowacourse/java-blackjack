package blackjack.domain.participant;

import blackjack.domain.vo.Result;

public class Player extends Participant {
    private static final int MAXIMUM_SCORE_FOR_ADDITIONAL_CARD = 21;
    private static final int INVALID_BETTING_MONEY_BOUNDARY = 0;
    private static final String INVALID_BETTING_MONEY = "배팅 금액은 양의 정수여야합니다.";

    private final int bettingMoney;

    public Player(String name) {
        super(name);
        this.bettingMoney = 0;
    }

    public Player(String name, int bettingMoney) {
        super(name);
        validateBettingMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateBettingMoney(int bettingMoney) {
        if (bettingMoney <= INVALID_BETTING_MONEY_BOUNDARY) {
            throw new IllegalArgumentException(INVALID_BETTING_MONEY);
        }
    }

    @Override
    public boolean isAbleToReceiveCard() {
        int minimumScore = calculateScoreWhenAceIsMinimum();
        return minimumScore < MAXIMUM_SCORE_FOR_ADDITIONAL_CARD;
    }

    public int calculateProfitMoney(Dealer dealer) {
        if (dealer.isBust()) {
            return calculateProfitMoneyWhenDealerBust();
        }
        if (dealer.isBlackJack()) {
            return calculateProfitMoneyWhenDealerBlackJack();
        }
        return calculateProfitMoneyWhenNormalSituation(dealer);

    }

    private int calculateProfitMoneyWhenDealerBust() {
        if (isBust()) {
            return -1 * bettingMoney;
        }
        return bettingMoney;
    }

    private int calculateProfitMoneyWhenDealerBlackJack() {
        if (isBlackJack()) {
            return bettingMoney;
        }
        return -1 * bettingMoney;
    }

    private int calculateProfitMoneyWhenNormalSituation(Dealer dealer) {
        if (isBust()) {
            return -1 * bettingMoney;
        }
        if (isBlackJack()) {
            return (int) (1.5 * bettingMoney);
        }
        if (this.calculateFinalScore() > dealer.calculateFinalScore()) {
            return bettingMoney;
        }
        return -1 * bettingMoney;
    }

    public Result judgeResult(Dealer dealer) {
        int dealerScore = dealer.calculateFinalScore();
        int playerScore = calculateFinalScore();
        return Result.judge(dealerScore, playerScore);
    }
}
