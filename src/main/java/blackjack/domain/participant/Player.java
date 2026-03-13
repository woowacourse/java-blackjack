package blackjack.domain.participant;

public final class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        return !isBurst() && !isBlackjack();
    }

}
