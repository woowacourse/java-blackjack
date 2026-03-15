import controller.BlackjackController;
import controller.GameMode;
import model.BlackjackGame;
import model.card.SimpleCardShuffler;

public class BlackjackApplication {

    public static void main(String[] args) {
        new BlackjackController<>(
                BlackjackGame.setup(new SimpleCardShuffler()),
                GameMode.toBettingMode()
        ).run();
    }
}
