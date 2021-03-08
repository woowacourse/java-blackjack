package blackjack.domain.user;

public class Player extends User {
    boolean isStay = false;

    public Player(String name) {
        super(name);
    }

    public void stay() {
        this.isStay = true;
    }

    public boolean isStay() {
        return this.isStay;
    }

    public boolean isBust() {
        return this.cards.isBust();
    }
}
