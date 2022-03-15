package blackjack.domain.participant;

public class Player extends Participant {

    private boolean isTurnEnd = false;

    public Player(String name) {
        super(name);
    }

    public boolean canHit() {
        return !isBlackjack() && !isBust() && !isTurnEnd;
    }

    public void stay() {
        isTurnEnd = true;
    }
}
