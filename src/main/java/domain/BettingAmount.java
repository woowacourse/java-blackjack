package domain;

public class BettingAmount {

    private static final int MIN_AMOUNT = 10000;
    private static final String MIN_BETTING_AMOUNT_ERROR_MESSAGE = "초기 배팅 금액은 10000원 이상이어야 합니다.";
    
    private final int revenue;

    private BettingAmount(int money) {
        this.revenue = money;
    }

    public static BettingAmount fromPlayer(int money) {
        validate(money);
        return new BettingAmount(money);
    }

    private static void validate(int money) {
        if (money < MIN_AMOUNT) {
            throw new IllegalArgumentException(MIN_BETTING_AMOUNT_ERROR_MESSAGE);
        }
    }

    public int getRevenue() {
        return revenue;
    }

}
