package domain.user;

import domain.game.GameResult;

public record BetAmount(int value) {
    private static final double BLACK_JACK_MULTIPLIER = 1.5;
    private static final double WIN_MULTIPLIER = 1;
    private static final double DRAW_MULTIPLIER = 0;
    private static final double LOSE_MULTIPLIER = -1;

    public BetAmount change(GameResult gameResult) {
        return switch (gameResult) {
            case WIN -> times(WIN_MULTIPLIER);
            case DRAW -> times(DRAW_MULTIPLIER);
            case LOSE -> times(LOSE_MULTIPLIER);
        };
    }

    public BetAmount changeByBlackJack() {
        return times(BLACK_JACK_MULTIPLIER);
    }

    private BetAmount times(double multiplier) {
        return new BetAmount((int) (this.value * multiplier));
    }
}
