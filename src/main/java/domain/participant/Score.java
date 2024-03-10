package domain.participant;

public record Score(int value) {
    public boolean isBigger(int otherScore) {
        return this.value > otherScore;
    }

    public boolean isLower(int otherScore) {
        return this.value < otherScore;
    }

    public boolean isLowerOrEqual(int otherScore) {
        return this.value <= otherScore;
    }
}
