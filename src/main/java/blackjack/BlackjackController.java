package blackjack;

import blackjack.config.GameConfig;
import blackjack.constant.UserAction;
import blackjack.gametable.BlackjackTable;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(GameConfig gameConfig) {
        this.inputView = gameConfig.getInputView();
        this.outputView = gameConfig.getOutputView();
    }

    public void run() {
        List<String> playerNames = inputView.readParticipantsNames();
        BlackjackTable gameTable = new BlackjackTable(playerNames);
        gameTable.initializeGame();

        for (String playerName : gameTable.getPlayerNames()) {
            int betAmount = inputView.readParticipantsBetAmount(playerName);
            gameTable.updateBetAmount(playerName, betAmount);
        }

        outputView.printInitialGameSettings(gameTable);

        for (String playerName : gameTable.getPlayerNames()) {
            UserAction userAction = inputView.readOneMoreCardResponse(playerName);
            while (userAction.equals(UserAction.HIT)) {
                gameTable.addCardTo(playerName);
                outputView.printPlayerCards(gameTable, playerName);
                userAction = inputView.readOneMoreCardResponse(playerName);
            }
        }

        if (gameTable.isDealerDrawCard()) {
            outputView.printDealerOneMoreCardMessage();
        }
        gameTable.determineDealerAdditionalCard();

        outputView.printGameSummary(gameTable);

        gameTable.calculateTotalPayout();
        outputView.printGameResult(gameTable);
    }

}
