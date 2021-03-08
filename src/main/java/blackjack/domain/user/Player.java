package blackjack.domain.user;

public class Player extends User {

    private static final int BLACKJACK_UPPER_BOUND = 21;

    public Player(String name) {
        super(name);
    }

    public boolean isNotBust() {
        return this.cards.calculateTotalScore() <= BLACKJACK_UPPER_BOUND;
    }
}
