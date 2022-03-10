package blackjack.domain;

public class Player extends Gamer {

    private static final int MAX_SCORE = 21;
    private static final int WIN = 1;
    private static final int DRAW = 0;
    private static final int LOSE = -1;

    public Player(Name name) {
        super(name);
    }

    @Override
    public boolean isValidRange() {
        return getScore() < MAX_SCORE;
    }

    @Override
    public int compare(Gamer dealer) {
        if (isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return compareScore(dealer);
    }

    private int compareScore(Gamer dealer) {
        if (isBLACKJACK() && dealer.isBLACKJACK()) {
            return DRAW;
        }
        if (dealer.isBLACKJACK()) {
            return LOSE;
        }
        if (isBLACKJACK()) {
            return WIN;
        }
        return getScore() - dealer.getScore();
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
