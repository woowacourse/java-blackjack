package domain;

import java.util.List;

public record Profits(
        List<RoundResult> results,
        BetMoney dealerProfit
) {
}
