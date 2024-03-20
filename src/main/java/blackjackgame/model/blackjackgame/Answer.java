package blackjackgame.model.blackjackgame;

public class Answer {

    private final boolean isHit;

    public Answer(boolean answer) {
        this.isHit = answer;
    }

    public boolean isHit() {
        return isHit;
    }
}
