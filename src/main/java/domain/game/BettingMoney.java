package domain.game;

public class BettingMoney {
    
    private final int money;

    private BettingMoney(final int money) {
        this.money = money;
    }

    public static BettingMoney create(final String money) {
        int validMoney = validType(money);
        return new BettingMoney(validMoney);
    }

    private static int validType(final String money) {
        int validMoney;
        try {
            validMoney = Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액은 정수 값이어야 합니다.");
        }
        return validMoney;
    }
}
