package model.participant;

public class Player extends Participant {

    public Player(String nickname) {
        super(nickname);
    }

    @Override
    public boolean canReceiveCard() {
        return !isBust();
    }
}
