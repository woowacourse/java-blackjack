package model;

public class GameMoney {
    private static final double LOSE_RATE = -1;
    private static final double DRAW_RATE = 0;

    private final int value;

    public GameMoney(int value) {
        validateGameMoneyOverZero(value);
        this.value = value;
    }

    private void validateGameMoneyOverZero(int value) {
        if(value < 1) {
            throw new IllegalArgumentException("게임 배팅 금액은 0보다 커야 합니다.");
        }
    }

    public int calculateRevenue(Outcome outcome, BlackJackState blackJackState) {
        if(outcome == Outcome.LOSE) {
            return (int) (value * LOSE_RATE);
        }
        if(outcome == Outcome.DRAW) {
            return (int) (value * DRAW_RATE);
        }
        return blackJackState.calculateBattingRatio(value);
    }
}
