import controller.BlackJackController;
import domain.generator.RandomCardNumberGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackJackController game = new BlackJackController(
                new InputView(),
                new OutputView()
        );

        game.run(new RandomCardNumberGenerator());
    }
}
