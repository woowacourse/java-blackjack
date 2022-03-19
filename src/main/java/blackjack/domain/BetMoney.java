package blackjack.domain;

public class BetMoney {

    private final int betMoney;

    public BetMoney(int betMoney) {
        checkNegative(betMoney);
        this.betMoney = betMoney;
    }

    private void checkNegative(int betMoney) {
        if (betMoney < 0) {
            throw new IllegalArgumentException("[ERROR] 배팅금액은 음수일 수 없습니다.");
        }
    }

    public int getBetMoney() {
        return betMoney;
    }

}
