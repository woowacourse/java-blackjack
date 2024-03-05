package blackjack.model;

public class Dealer extends Player {
    public Dealer() {
        super("딜러");
    }

    public boolean canReceive() {
        if (cards.calculateScore() <= 16) {
            return true;
        }
        return false;
    }
}
