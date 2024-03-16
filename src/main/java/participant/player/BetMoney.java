package participant.player;

public class BetMoney {

    private static final int MIN_BET_MONEY = 3000;
    private static final int MAX_BET_MONEY = 400000;

    private final int betAmount;

    public BetMoney(int money) {
        validateCanBetMoney(money);
        this.betAmount = money;
    }

    private void validateCanBetMoney(int money) {
        if (money < MIN_BET_MONEY || MAX_BET_MONEY < money) {
            throw new IllegalArgumentException("베팅 금액은 " + MIN_BET_MONEY + "원 에서 " + MAX_BET_MONEY + "까지만 가능합니다.");
        }
    }

    public int betMoneyResult(double percent) {
        return (int) (betAmount * percent);
    }
}
