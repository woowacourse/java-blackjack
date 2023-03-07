import controller.BlackJackController;
import domain.service.BlackJackResultMaker;
import domain.service.CardDistributor;
import domain.service.RandomCardGenerator;

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
