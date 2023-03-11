package blackjack.domain;

public class DealerResult {

    private int dealerResult = 0;

    public void add(int dealerBenefit) {
        dealerResult += dealerBenefit;
    }

    public int getDealerResult() {
        return dealerResult;
    }
}
