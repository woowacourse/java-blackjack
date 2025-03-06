package blackjack;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new InputView(), new OutputView());
        blackjackController.run();
    }
}
