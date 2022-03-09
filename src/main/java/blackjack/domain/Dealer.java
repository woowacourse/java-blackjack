package blackjack.domain;

public class Dealer extends Gamer {

    private static final Name DEALER_NAME = new Name("딜러");
    private static final int MAX_SCORE = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isValidRange() {
        return getScore() < MAX_SCORE;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
