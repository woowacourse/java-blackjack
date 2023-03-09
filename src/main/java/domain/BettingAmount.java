package domain;

import java.util.Objects;

public class BettingAmount {

    private static final int MIN_AMOUNT = 10000;
    private static final BettingAmount INITIAL = new BettingAmount(0);
    private static final double BLACKJACK_MULTIPLE = 1.5;
    private static final int WIN_MULTIPLE = 1;
    private static final int LOSE_MULTIPLE = -1;
    private static final String MIN_BETTING_AMOUNT_ERROR_MESSAGE = "초기 배팅 금액은 10000원 이상이어야 합니다.";

    private final double revenue;

    private BettingAmount(double money) {
        this.revenue = money;
    }

    public static BettingAmount fromPlayer(double money) {
        validate(money);
        return new BettingAmount(money);
    }

    private static void validate(double money) {
        if (money < MIN_AMOUNT) {
            throw new IllegalArgumentException(MIN_BETTING_AMOUNT_ERROR_MESSAGE);
        }
    }

    public BettingAmount wonBlackJack() {
        return new BettingAmount(revenue * BLACKJACK_MULTIPLE);
    }

    public BettingAmount won() {
        return new BettingAmount(revenue * WIN_MULTIPLE);
    }

    public BettingAmount lostOrBust() {
        return new BettingAmount(revenue * LOSE_MULTIPLE);
    }

    public double getRevenue() {
        return revenue;
    }

    public static BettingAmount getDealerInitialAmount() {
        return INITIAL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BettingAmount that = (BettingAmount) o;
        return Double.compare(that.revenue, revenue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(revenue);
    }

}
