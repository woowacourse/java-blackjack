package blackjack;

import blackjack.config.GameConfig;
import blackjack.gametable.BlackjackGame;
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
        BlackjackGame gameTable = new BlackjackGame(playerNames);

        for (String playerName : gameTable.getPlayerNames()) {
            int betAmount = inputView.readParticipantsBetAmount(playerName);
            gameTable.updateBetAmount(playerName, betAmount);
        }

        outputView.printInitialGameSettings(gameTable);
        gameTable.playGame(inputView, outputView);
        outputView.printGameSummary(gameTable);
        outputView.printGameResult(gameTable);
    }
}
