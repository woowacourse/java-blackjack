import controller.BlackJackController;
import domain.model.BlackJackResultMaker;
import domain.model.CardDistributor;
import domain.model.RandomCardGenerator;

public class Application {

    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController(makeCardDistributor(),
            new BlackJackResultMaker());
        blackJackController.play();
    }

    private static CardDistributor makeCardDistributor() {
        return new CardDistributor(new RandomCardGenerator());
    }
}
