package blackjack.domain.user;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    public boolean isBust() {
        return this.cards.isBust();
    }
}
