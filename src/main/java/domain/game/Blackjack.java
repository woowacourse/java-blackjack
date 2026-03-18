package domain.game;

public class Blackjack implements HandState {

    @Override
    public Outcome versus(HandState other) {
        if (other instanceof Blackjack) {
            return Outcome.TIE;
        }
        return Outcome.BLACKJACK_WIN;
    }

    @Override
    public boolean canHit() {
        return false;
    }
}
