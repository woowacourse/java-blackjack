package domain.user;

import domain.game.GameResult;

public record BetAmount(int value) {
    public BetAmount change(GameResult gameResult) {
        return switch (gameResult) {
            case WIN -> times(1);
            case DRAW -> times(0);
            case LOSE -> times(-1);
        };
    }

    private BetAmount times(double multiplier) {
        return new BetAmount((int) (this.value * multiplier));
    }
}
