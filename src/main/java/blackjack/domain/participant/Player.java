package blackjack.domain.participant;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public boolean canHit(){
        return getScore() < GOAL_SCORE;
    }

    public boolean isWin(int score) {
        if (getScore() > GOAL_SCORE) {
            return false;
        }
        if (score > GOAL_SCORE) {
            return true;
        }
        return getScore() >= score;
    }
}
