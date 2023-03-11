package blackjack.domain.result;

import blackjack.domain.bet.Money;

public class DealerResult {

    private int benefit = 0;

    public void add(Money dealerBenefit) {
        benefit += dealerBenefit.getValue();
    }

    public int getBenefit() {
        return benefit;
    }
}
