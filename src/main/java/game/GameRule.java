package game;

public enum GameRule {
    BLACK_JACK(21),
    DEALER_STAY(16);

    private final int value;

    GameRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean checkBust(int score) {
        return score > GameRule.BLACK_JACK.getValue();
    }
}
