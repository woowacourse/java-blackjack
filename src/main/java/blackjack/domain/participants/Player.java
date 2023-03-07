package blackjack.domain.participants;

public class Player extends Participant {

    public Player(final String name) {
        super(name);
    }

    @Override
    public boolean isAvailable() {
        return isSafe();
    }
}
