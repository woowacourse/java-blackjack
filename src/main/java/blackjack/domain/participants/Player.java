package blackjack.domain.participants;


import java.util.ArrayList;

public class Player extends GameParticipant {

    public Player(Name name) {
        super(name, new Hands(new ArrayList<>()));
    }

    @Override
    public Result takeOn(GameParticipant participant) {
        if (isTie(participant)) {
            return Result.TIE;
        }
        if (isWin(participant)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private boolean isWin(GameParticipant participant) {
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
