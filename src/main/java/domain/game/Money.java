package domain.game;

public record Money(int value) {
    private static final double BLACK_JACK_MULTIPLIER = 1.5;
    private static final double WIN_MULTIPLIER = 1;
    private static final double DRAW_MULTIPLIER = 0;
    private static final double LOSE_MULTIPLIER = -1;

    public Money change(GameResult gameResult) {
        return switch (gameResult) {
            case WIN -> times(WIN_MULTIPLIER);
            case DRAW -> times(DRAW_MULTIPLIER);
            case LOSE -> times(LOSE_MULTIPLIER);
        };
    }

    public Money changeByBlackJack() {
        return times(BLACK_JACK_MULTIPLIER);
    }

    private Money times(double multiplier) {
        return new Money((int) (this.value * multiplier));
    }
}
