import ui.BlackjackController;
import ui.view.InputView;
import ui.view.ResultView;

public class Main {
    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new InputView(), new ResultView());
        blackjackController.run();
    }
}
