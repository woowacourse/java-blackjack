package blackjack.domain.player;

import blackjack.domain.game.GameResult;

public record BetAmount(int amount) {

    public BetAmount {
        validateAmount(amount);
    }

    private void validateAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("배팅 금액은 음수일 수 없습니다.");
        }
    }

    public Profit getProfit(GameResult gameResult) {
        return Profit.calculateFrom(amount, gameResult);
    }
}
