package blackjack.domain.participant;

import blackjack.domain.game.ProfitWeight;

public class Player extends Participant {
    private static final int MAXIMUM_SCORE_FOR_ADDITIONAL_CARD = 21;
    private static final int INVALID_BETTING_MONEY_BOUNDARY = 0;
    private static final String INVALID_BETTING_MONEY = "배팅 금액은 양의 정수여야합니다.";

    private final int bettingMoney;

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
        ProfitWeight profitWeight = ProfitWeight.findProfitWeight(dealer, this);
        return (int) (bettingMoney * profitWeight.getWeight());
    }
}
