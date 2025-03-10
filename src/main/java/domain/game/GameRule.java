package domain.game;

public enum GameRule {
    BUST_THRESHOLD(21),
    DEALER_STAY(16);

    private final int value;

    GameRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean checkBust(int score) {
        return score > GameRule.BUST_THRESHOLD.getValue();
    }
}
