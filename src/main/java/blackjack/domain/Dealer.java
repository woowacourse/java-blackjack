package blackjack.domain;

public class Dealer extends AbstractPlayer implements Player {

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
    public int compareWinning(Player player) {
        if (player.isBust()) {
            return WIN;
        }
        if (isBust()) {
            return LOSE;
        }
        return compareScore(player);
    }

    private int compareScore(Player player) {
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
