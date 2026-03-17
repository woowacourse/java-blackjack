package domain.game;

public class Bust implements HandState {

    @Override
    public Outcome versus(HandState other) {
        return Outcome.LOSE;
    }
}
