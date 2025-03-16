package blackjack;

import blackjack.config.GameConfig;
import blackjack.constant.UserAction;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackGame {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGame(GameConfig gameConfig) {
        this.inputView = gameConfig.getInputView();
        this.outputView = gameConfig.getOutputView();
    }

    public void run() {
        List<String> playerNames = inputView.readParticipantsNames();
        BlackjackTable gameTable = new BlackjackTable(playerNames);
        gameTable.initializeGame();
        outputView.printInitialGameSettings(gameTable);

        for (String playerName : gameTable.getPlayerNames()) {
            UserAction userAction = inputView.readOneMoreCardResponse(playerName);
            while (userAction.equals(UserAction.STAND)) {
                gameTable.addCardTo(playerName);
                outputView.printPlayerCards(gameTable, playerName);
                userAction = inputView.readOneMoreCardResponse(playerName);
            }
        }

        gameTable.determineDealerAdditionalCard();

        if (gameTable.isDealerDrawCard()) {
            outputView.printDealerOneMoreCardMessage();
        }

        outputView.printGameSummary(gameTable);
        outputView.printGameResult(players.deriveResults(dealer.sumCardScores()));
    }

}
