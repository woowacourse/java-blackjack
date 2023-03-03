import domain.CardBox;
import domain.RandomCardNumberGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackJackGame game = new BlackJackGame(
                new InputView(),
                new OutputView(),
                new CardBox(),
                new RandomCardNumberGenerator()
        );

        game.run();
    }
}
