import controller.BlackJackController;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        new BlackJackController(inputView, outputView).gameStart();
    }
}
