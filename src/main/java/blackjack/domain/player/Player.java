package blackjack.domain.player;

public class Player extends Participant {

    public static final int HIT_THRESHOLD = 21;

    public Player(PlayerName name, Hand hand) {
        super(name, hand);
    }

    @Override
    public boolean canHit() {
        return hand.calculateCardSummation() <= HIT_THRESHOLD;
    }
}
