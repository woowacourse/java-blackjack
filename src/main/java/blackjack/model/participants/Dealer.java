package blackjack.model.participants;

import blackjack.model.results.PlayerResult;
import blackjack.model.results.Result;

public class Dealer extends Participant {
    private static final int STAND_THRESHOLD = 17;

    @Override
    public boolean canHit() {
        return cards.getScore() < STAND_THRESHOLD;
    }

    public int calculateDealerProfit(PlayerResult playerResult) {
        return playerResult.getResult().entrySet().stream()
                .mapToInt(entry -> convertToDealerBetResult(entry.getValue(), entry.getKey().getBetAmount()))
                .sum();
    }

    private int convertToDealerBetResult(Result playerResult, int betAmount) {
        if (playerResult == Result.WIN_BY_BLACKJACK) {
            return Result.LOSE_BY_BLACKJACK.getProfit(betAmount);
        }
        if (playerResult == Result.WIN) {
            return Result.LOSE.getProfit(betAmount);
        }
        if (playerResult == Result.LOSE) {
            return Result.WIN.getProfit(betAmount);
        }
        return Result.PUSH.getProfit(betAmount);
    }
}
