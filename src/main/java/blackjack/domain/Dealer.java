package blackjack.domain;

public class Dealer extends Gamer {

    private static final Name DEALER_NAME = new Name("딜러");
    private static final int MAX_SCORE = 17;
    private static final int WIN = 1;
    private static final int DRAW = 0;
    private static final int LOSE = -1;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isValidRange() {
        return getScore() < MAX_SCORE;
    }

    @Override
    public int compare(Gamer player) {
        if (player.isBust()) {
            return WIN;
        }
        if (isBust()) {
            return LOSE;
        }
        return compareScore(player);
    }

    private int compareScore(Gamer player) {
        if (isBLACKJACK() && player.isBLACKJACK()) {
            return DRAW;
        }
        if (isBLACKJACK()) {
            return WIN;
        }
        if (player.isBLACKJACK()) {
            return LOSE;
        }
        return getScore() - player.getScore();
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
