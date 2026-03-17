import controller.BlackJackController;
import model.card.BlackJackDeck;
import model.BlackJackGame;

public class Main {

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(new BlackJackDeck());
        BlackJackController blackJackController = new BlackJackController(blackJackGame);

        blackJackController.playBlackJackGame();
    }
}
