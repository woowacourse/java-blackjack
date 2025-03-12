package participant;

public class Player extends Participant {

    public Player(String nickname, int betAmount) {
        super(nickname, betAmount);
    }

    @Override
    public boolean canReceiveCard() {
        return !isBust();
    }
}
