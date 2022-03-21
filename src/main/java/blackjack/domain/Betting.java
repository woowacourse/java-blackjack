package blackjack.domain;

public class Betting {

    private final int betMoney;

    public Betting(int betMoney) {
        validate(betMoney);
        this.betMoney = betMoney;
    }

    private void validate(int betMoney) {
        if (betMoney <= 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 0원 이하일 수 없습니다.");
        }
    }

    public int getBetMoney() {
        return betMoney;
    }
}
