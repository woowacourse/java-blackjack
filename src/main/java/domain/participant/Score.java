package domain.participant;

public record Score(int value) {

    public boolean isBigger(final Score other) {
        return this.value > other.value;
    }
}
