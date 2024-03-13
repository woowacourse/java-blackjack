package blackjack.domain.participant;

public class BetMoney {

    private static final int MINIMUM_BET_MONEY = 0;

    private final int betMoney;

    public BetMoney(int betMoney) {
        if (betMoney < MINIMUM_BET_MONEY) {
            throw new IllegalArgumentException("배팅금액은 0이상이어야 합니다.");
        }
        this.betMoney = betMoney;
    }

    public int getBetMoney() {
        return betMoney;
    }
}
