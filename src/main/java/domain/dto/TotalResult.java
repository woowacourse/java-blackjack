package domain.dto;

import domain.BetMoney;

import java.util.List;

public record TotalResult(
        List<RoundResult> results,
        BetMoney dealerProfit
) {
}
