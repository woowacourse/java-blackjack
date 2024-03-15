package blackjack.domain.player;

public class Player extends Participant {
    private static final int BLACKJACK_CONDITION = 21;

    private Player(final Hand hand, final Name name) {
        super(hand, name);
    }

    public Player(final Name name) {
        this(new Hand(), name);
    }

    public boolean isBlackjack() {
        return hand.hasExactlyTwoCards() && hand.getScore() == BLACKJACK_CONDITION;
    }
}
