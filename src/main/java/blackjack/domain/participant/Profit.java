package blackjack.domain.participant;

import java.math.BigDecimal;

public class Profit {

    private BigDecimal amount;

    private Profit(final BigDecimal amount) {
        this.amount = amount;
    }

    public static Profit initProfit() {
        return new Profit(BigDecimal.ZERO);
    }

    public void earn(final BigDecimal win, final BigDecimal betting) {
        this.amount = this.amount.add(win.subtract(betting));
    }

    public void earn(final BigDecimal betting) {
        this.amount = this.amount.add(betting);
    }

    public void earnBlackjack(final BigDecimal blackjack, final BigDecimal betting) {
        this.amount = this.amount.add(blackjack.subtract(betting));
    }

    public void lose(final BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }

    @Override
    public String toString() {
        return formatBigDecimal(amount);
    }

    public String formatBigDecimal(BigDecimal number) {
        if (number.scale() <= 0) {
            return number.setScale(0).toString(); // 소수점 이하 자리가 없을 때는 정수부분만 출력
        } else {
            return number.stripTrailingZeros().toPlainString(); // 소수점 이하 자리가 있을 때는 해당 자리까지만 출력
        }
    }
}
