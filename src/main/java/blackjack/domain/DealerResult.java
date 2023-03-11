package blackjack.domain;

public class DealerResult {

    private int dealerResult = 0;

    public void add(Money dealerBenefit) {
        dealerResult += dealerBenefit.getValue();
    }

    public int getDealerResult() {
        return dealerResult;
    }
}
