import domain.BlackjackGame;
import java.util.List;
import presentation.ui.InputView;
import presentation.ui.OutputView;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BlackjackGame game = new BlackjackGame();

        List<String> playerNames = inputView.readPlayerNames();
        game.joinPlayerToGame(playerNames);
        outputView.printInitialStatus(game.playerHands());
        game.playGame(playerNames, inputView, outputView);
        if (game.checkDealerDrawable()) {
            game.dealerDrawCard();
            outputView.printDealerDrawResult();
        }
        outputView.printFinalMemberStatus(game.playerHands());
        outputView.printGameResult(game.getGameResults());
    }
}
