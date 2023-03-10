import controller.BlackJackController;
import domain.service.BlackJackGame;
import domain.service.CardDistributor;
import domain.service.ProfitCalculator;
import domain.service.RandomCardGenerator;
import view.IOView;

public class Application {

    public static void main(String[] args) {
        makeBlackJackController().play();
    }

    private static BlackJackController makeBlackJackController() {
        return new BlackJackController(makeBlackJackGame(), new IOView());
    }

    private static BlackJackGame makeBlackJackGame() {
        return new BlackJackGame(makeCardDistributor(), new ProfitCalculator());
    }

    private static CardDistributor makeCardDistributor() {
        return new CardDistributor(new RandomCardGenerator());
    }
}
