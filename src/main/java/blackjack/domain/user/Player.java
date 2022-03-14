package blackjack.domain.user;

public class Player extends User {

    private static final int TWENTY_ONE = 21;
    private static final int WIN = 1;
    private static final int DRAW = 0;
    private static final int LOSE = -1;

    public Player(UserName playerName) {
        super(playerName);
    }

    @Override
    public boolean isValidRange() {
        return getScore() < TWENTY_ONE;
    }

    @Override
    public int compare(User dealer) {
        if (isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return compareScore(dealer);
    }

    private int compareScore(User dealer) {
        if (isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }
        if (dealer.isBlackjack()) {
            return LOSE;
        }
        if (isBlackjack()) {
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
