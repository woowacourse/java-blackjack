import controller.GameController;
import domain.BlackJackGame;
import view.InputView;

public class Application {

    public static void main(String[] args) {
        String participantNames = InputView.getParticipantNames();
        BlackJackGame blackJackGame = new BlackJackGame(participantNames);
        GameController gameController = new GameController(blackJackGame);
        gameController.ready();
        gameController.play();
        gameController.printFinalGameResult();
    }
}
