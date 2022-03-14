package blackjack.domain.participant;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public boolean canHit() {
        return !isBust();
    }
}
