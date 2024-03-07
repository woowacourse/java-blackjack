import controller.BlackJackController;
import view.InputView;
import view.OutputView;

public class Main {
    public static void main(String[] args) {
        new BlackJackController(new InputView(), new OutputView()).startGame();
    }
}
