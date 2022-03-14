package blackjack.domain.user;

public class Dealer extends User {

    private static final UserName DEALER_NAME = new UserName("딜러");
    private static final int SEVEN_TEEN = 17;
    private static final int WIN = 1;
    private static final int DRAW = 0;
    private static final int LOSE = -1;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isValidRange() {
        return getScore() < SEVEN_TEEN;
    }

    @Override
    public int compare(User player) {
        if (player.isBust()) {
            return WIN;
        }
        if (isBust()) {
            return LOSE;
        }
        return compareScore(player);
    }

    private int compareScore(User player) {
        if (isBlackjack() && player.isBlackjack()) {
            return DRAW;
        }
        if (isBlackjack()) {
            return WIN;
        }
        if (player.isBlackjack()) {
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
