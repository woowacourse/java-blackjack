import domain.BlackjackGame;
import java.util.List;
import presentation.ui.InputView;
import presentation.ui.OutputView;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        List<String> playerNames = inputView.readPlayerNames();
        BlackjackGame game = new BlackjackGame(playerNames);
        game.initialDeal(playerNames);
        outputView.printInitialStatus(game.getDealerName(), game.memberFirstHands());
        for (String playerName : playerNames) {
            askToDraw(playerName, inputView, outputView, game);
        }
        printResult(outputView, game);
    }

    private static void askToDraw(String playerName, InputView inputView, OutputView outputView, BlackjackGame game) {
        while (game.isContinuable(playerName) && inputView.playContinue(playerName)) {
            game.drawPlayer(playerName);
            outputView.printHandCard(playerName, game.getCardNames(playerName));
        }
    }


    private static void printResult(OutputView outputView, BlackjackGame game) {
        if (game.canDealerDraw()) {
            game.dealerDrawCard();
            outputView.printDealerDrawResult();
        }
        outputView.printFinalMemberStatus(game.memberHands());
        outputView.printGameResult(game.getGameResults());
    }
}