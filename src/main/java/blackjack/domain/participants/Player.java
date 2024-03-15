package blackjack.domain.participants;


import java.util.ArrayList;

public class Player extends GameParticipant {

    public Player(Name name) {
        super(name, new Hands(new ArrayList<>()));
    }

    @Override
    protected boolean isWin(GameParticipant participant) {
        if (isBust()) {
            return false;
        }
        if (participant.isBust()) {
            return true;
        }
        return calculateScore() > participant.calculateScore();
    }

    @Override
    public boolean canHit() {
        return this.hands.calculateScore() < MAX_SCORE;
    }
}
