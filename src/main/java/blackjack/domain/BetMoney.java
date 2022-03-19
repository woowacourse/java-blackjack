package blackjack.domain;

public class BetMoney {

    private final long betMoney;

    public BetMoney(final long betMoney) {
        checkNegative(betMoney);
        this.betMoney = betMoney;
    }

    private void checkNegative(final long betMoney) {
        if (betMoney < 0) {
            throw new IllegalArgumentException("[ERROR] 배팅금액은 음수일 수 없습니다.");
        }
    }

    public long getBetMoney() {
        return betMoney;
    }

}
