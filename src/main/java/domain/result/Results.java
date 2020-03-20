package domain.result;

import domain.user.Money;

import java.util.List;

public class Results {
    private final List<Result> results;
    private final Money dealerProfit;

    private Results(List<Result> results, Money dealerProfit) {
        this.results = results;
        this.dealerProfit = dealerProfit;
    }

    public static Results of(List<Result> results, Money dealerProfit) {
        return new Results(results, dealerProfit);
    }
}
