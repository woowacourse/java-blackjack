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
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);

        for (String playerName : blackjackGame.getPlayerNames()) {
            int betAmount = inputView.readParticipantsBetAmount(playerName);
            blackjackGame.updateBetAmount(playerName, betAmount);
        }

        outputView.printInitialGameSettings(blackjackGame);
        blackjackGame.playGame(inputView, outputView);
        outputView.printGameSummary(blackjackGame);
        outputView.printGameResult(blackjackGame);
    }
}
