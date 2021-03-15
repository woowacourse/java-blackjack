package blackjack.domain.player;

import java.util.Objects;

public class BettingMoney {

    public static final int INVERSE_SIGN = -1;
    private int bettingMoney;

    public BettingMoney(String bettingMoney) {
        this(ChangeToInt(bettingMoney));
    }

    public BettingMoney(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    private static int ChangeToInt(String input) {
        validateEmpty(input);
        validateNumber(input);
        return Integer.parseInt(input);
    }

    private static void validateEmpty(String money) {
        if (money.isEmpty()) {
            throw new IllegalArgumentException("공백을 입력으로 받을 수 없습니다.");
        }
    }

    private static void validateNumber(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력하세요.");
        }
    }

    public BettingMoney calculateProfit(double yield) {
        return new BettingMoney((int) (bettingMoney * yield));
    }

    public BettingMoney inverseMoney() {
        return new BettingMoney(INVERSE_SIGN * bettingMoney);
    }

    public BettingMoney addMoney(BettingMoney bettingMoney) {
        return new BettingMoney(this.bettingMoney + bettingMoney.bettingMoney);
    }

    public int getBettingMoney() {
        return bettingMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BettingMoney bettingMoney = (BettingMoney) o;
        return this.bettingMoney == bettingMoney.bettingMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bettingMoney);
    }
}
