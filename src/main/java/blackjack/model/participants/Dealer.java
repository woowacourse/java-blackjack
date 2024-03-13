package blackjack.model.participants;

import blackjack.model.results.PlayerResult;
import blackjack.model.results.Result;
import blackjack.vo.Money;

public class Dealer extends Participant {
    private static final int STAND_THRESHOLD = 17;

    @Override
    public boolean canHit() {
        return cards.getScore() < STAND_THRESHOLD;
    }

    public Money calculateDealerProfit(PlayerResult playerResult) {
        int dealerProfit = playerResult.getResults().entrySet().stream()
                .map(entry -> convertToDealerProfit(entry.getValue(), entry.getKey().getBetAmount()))
                .mapToInt(Money::value)
                .sum();
        return new Money(dealerProfit);
    }

    private Money convertToDealerProfit(Result playerResult, Money betAmount) {
        if (playerResult == Result.WIN_BY_BLACKJACK) {
            return Result.LOSE_BY_BLACKJACK.calculateProfit(betAmount);
        }
        if (playerResult == Result.WIN) {
            return Result.LOSE.calculateProfit(betAmount);
        }
        if (playerResult == Result.LOSE) {
            return Result.WIN.calculateProfit(betAmount);
        }
        return Result.PUSH.calculateProfit(betAmount);
    }
}
