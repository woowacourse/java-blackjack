import controller.GameController;
import domain.Game;
import view.InputView;

public class Application {

    public static void main(String[] args) {
        String participantNames = InputView.getParticipantNames();
        Game game = new Game(participantNames);
        GameController gameController = new GameController(game);
        gameController.ready();
        gameController.play();
        gameController.printFinalGameResult();
    }
}
