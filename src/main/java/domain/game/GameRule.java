package domain.game;

public enum GameRule {
    BLACKJACK(21),
    DEALER_HIT_LIMIT(16);

    private final int number;

    GameRule(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
