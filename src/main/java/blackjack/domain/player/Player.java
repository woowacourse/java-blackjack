package blackjack.domain.player;

public class Player extends User {

    public Player(final UserName userName) {
        super(userName);
    }

    @Override
    public boolean canHit() {
        return isNotFinished();
    }
}
