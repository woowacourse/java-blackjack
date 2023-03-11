import controller.BlackJackController;
import domain.RandomCardGenerator;

public class Application {

    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController(new RandomCardGenerator());
        blackJackController.play();
    }
}
