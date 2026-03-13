import controller.BlackjackController;
import controller.GameMode;
import model.BlackjackService;
import model.card.CardShuffler;
import model.card.SimpleCardShuffler;

public class BlackjackApplication {

    public static void main(String[] args) {
        CardShuffler cardShuffler = new SimpleCardShuffler();
        BlackjackService blackjackService = BlackjackService.createDefaultService(cardShuffler);
        GameMode gameMode = GameMode.toBettingMode();

        BlackjackController controller = new BlackjackController(blackjackService, gameMode);
        controller.run();
    }
}
