package blackjack.domain.participant;

public class Player extends Participant {

    public Player(Nickname nickname) {
        super(nickname);
    }

    @Override
    public boolean canDraw() {
        return !state.isFinished();
    }
}
