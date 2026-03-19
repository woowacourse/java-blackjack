package blackjack.domain.participant;

public final class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public boolean winsAgainst(Dealer other) {
        if (other.isBurst()) {
            return true;
        }
        if (this.isBurst()) {
            return false;
        }
        return other.getScore() < this.getScore();
    }

    @Override
    public boolean canDraw() {
        return !isBurst() && !isBlackjack();
    }

}
