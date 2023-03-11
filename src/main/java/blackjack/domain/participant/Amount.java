package blackjack.domain.participant;

import blackjack.domain.game.WinningResult;

import java.math.BigDecimal;

public class Amount {

    private BigDecimal amount;

    public Amount(String amount) {
        this.amount = new BigDecimal(amount);
    }

    public void calculateAmountByResult(WinningResult winningResult) {
        this.amount = amount.multiply(winningResult.getMagnification());
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
