import controller.MainController;
import domain.card.shuffler.RandomCardsShuffler;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        MainController mainController = new MainController(InputView.getInstance(), OutputView.getInstance());
        mainController.run(new RandomCardsShuffler());
    }
}
