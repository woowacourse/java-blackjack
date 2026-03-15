import controller.BlackjackController;
import controller.GameMode;
import model.BlackjackGame;
import model.card.CardShuffler;
import model.card.SimpleCardShuffler;

public class BlackjackApplication {

    public static void main(String[] args) {
        CardShuffler cardShuffler = new SimpleCardShuffler();
        BlackjackGame blackjackGame = BlackjackGame.setup(cardShuffler);
        GameMode gameMode = GameMode.toBettingMode();

        BlackjackController controller = new BlackjackController(blackjackGame, gameMode);
        controller.run();
    }
}
