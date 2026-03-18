package domain.game;

public class Stay implements HandState {

    private final int score;

    public Stay(int score) {
        this.score = score;
    }

    @Override
    public Outcome versus(HandState other) {
        if (other instanceof Blackjack) {
            return Outcome.LOSE;
        }
        if (other instanceof Bust) {
            return Outcome.WIN;
        }
        Stay otherStay = (Stay) other;
        return compareScore(otherStay);
    }

    @Override
    public boolean canHit() {
        return true;
    }

    private Outcome compareScore(Stay other) {
        if (this.score > other.score) {
            return Outcome.WIN;
        }
        if (this.score == other.score) {
            return Outcome.TIE;
        }
        return Outcome.LOSE;
    }
}
