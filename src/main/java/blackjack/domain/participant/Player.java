package blackjack.domain.participant;

public class Player extends Participant {
    private static final int BLACKJACK_SCORE = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canReceiveCard() {
        return calculateScore() <= BLACKJACK_SCORE;
    }
}
