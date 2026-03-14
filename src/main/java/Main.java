import application.BlackjackService;
import presentation.BlackjackController;
import presentation.ui.BlackjackView;
import presentation.ui.InputView;
import presentation.ui.OutputView;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        BlackjackService blackjackService = new BlackjackService();
        BlackjackController blackjackController = new BlackjackController(blackjackService,
                new BlackjackView(inputView, outputView));

        blackjackController.executeGame();
    }
}
