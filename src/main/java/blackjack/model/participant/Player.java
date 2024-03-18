package blackjack.model.participant;

public class Player extends Gamer {

    Player(final Name name, final Hand hand) {
        super(name, hand);
    }

    public static Player of(final String rawName, final Hand hand) {
        return new Player(new Name(rawName), hand);
    }

    @Override
    public boolean canHit() {
        return hand.isUnderMaxScore();
    }
}
