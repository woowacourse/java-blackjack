package blackjack.domain.user;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    public boolean canHit() {
        return getScore().isNotBust() && !getScore().isBlackJack();
    }
}
