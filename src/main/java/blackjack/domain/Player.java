package blackjack.domain;

public class Player extends Participant{
    private Player(Name name, Hand hand) {
        super(name, hand);
    }

    public static Player of(Name name) {
        return new Player(name, Hand.init());
    }

    @Override
    public boolean canHit() {
        return !isBust();
    }
}

