package domain.game;

public interface HandState {

    Outcome versus(HandState other);
}
