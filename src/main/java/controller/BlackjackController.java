package controller;

public class BlackjackController {

    private GameContext gameContext;
    private EnterPlayerPhase enterPlayerPhase;

    public BlackjackController() {
        gameContext = new GameContext();
        enterPlayerPhase = new EnterPlayerPhase();
    }

    public void run() {
        enterPlayerPhase.execute(gameContext);

    }
}
