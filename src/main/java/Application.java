import controller.BlackJackController;
import domain.card.CardDistributor;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController(new InputView(), new OutputView(), new CardDistributor());
        blackJackController.playGame();
    }
}
