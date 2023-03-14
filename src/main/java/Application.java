import controller.BlackJackController;
import domain.service.BlackJackGame;
import domain.service.CardDistributor;
import domain.service.ProfitCalculator;
import domain.service.RandomCardGenerator;
import view.IOView;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        makeBlackJackController().play();
    }

    private static BlackJackController makeBlackJackController() {
        return new BlackJackController(makeBlackJackGame(), makeIOView());
    }

    private static IOView makeIOView() {
        return new IOView(new InputView(), new OutputView());
    }

    private static BlackJackGame makeBlackJackGame() {
        return new BlackJackGame(makeCardDistributor(), new ProfitCalculator());
    }

    private static CardDistributor makeCardDistributor() {
        return new CardDistributor(RandomCardGenerator.getInstance());
    }
}
