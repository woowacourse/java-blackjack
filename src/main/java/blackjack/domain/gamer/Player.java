package blackjack.domain.gamer;

public class Player extends Gamer {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canReceiveAdditionalCards() {
        return !isBust();
    }
}
