package blackjack.domain.result;

import blackjack.domain.bet.Money;

public class DealerResult {

    private int dealerResult = 0;

    public void add(Money dealerBenefit) {
        dealerResult += dealerBenefit.getValue();
    }

    public int getDealerResult() {
        return dealerResult;
    }
}
