package domain.participant;

public record Score(int value) {

    public boolean isBigger(final Score other) {
        return this.value > other.value;
    }

//    public boolean isEqual(final int other) {
//        return this.value == other;
//    }
}
