import application.BlackjackService;
import domain.Deck;
import domain.GameTable;
import presentation.BlackjackController;
import presentation.ui.InputView;
import presentation.ui.OutputView;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        GameTable gameTable = new GameTable();
        Deck deck = new Deck();

        BlackjackService blackjackService = new BlackjackService(gameTable, deck);
        BlackjackController blackjackController = new BlackjackController(blackjackService, inputView, outputView);

        blackjackController.executeGame();
    }
}
