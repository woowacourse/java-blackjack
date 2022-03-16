package blackjack.domain.participant;

public class BetMoney {

    private final int amount;

    public BetMoney(int betMoney) {
        checkRange(betMoney);
        this.amount = betMoney;
    }

    private void checkRange(int betMoney) {
        if (betMoney <= 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 1원 이상부터 가능합니다.");
        }
    }
}
