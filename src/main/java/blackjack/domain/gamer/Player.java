package blackjack.domain.gamer;

public class Player extends Participants {

    public Player(String name) {
        super(name);
    }

    public double profit() {
        return state.profit(money);
    }

    @Override
    public boolean canDraw() {
        return !state.isFinished();
    }
}
