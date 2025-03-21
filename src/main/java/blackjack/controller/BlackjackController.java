package blackjack.controller;

import blackjack.config.GameConfig;
import blackjack.constant.UserAction;
import blackjack.domain.BlackjackGame;
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

        playGame(blackjackGame);

        outputView.printGameSummary(blackjackGame);
        outputView.printGameResult(blackjackGame);
    }


    public void playGame(BlackjackGame blackjackGame) {
        while (blackjackGame.isPlaying()) {
            playPlayerTurn(blackjackGame);
        }
        playDealerTurn(blackjackGame);
    }


    private void playPlayerTurn(BlackjackGame blackjackGame) {
        String playerName = blackjackGame.findCurrentTurnPlayerName();
        while (inputView.readOneMoreCardResponse(playerName).equals(UserAction.HIT)) {
            blackjackGame.addCardTo(playerName);
            outputView.printPlayerCards(blackjackGame, playerName);
        }
        blackjackGame.endPlayerTurn(playerName);
    }

    private void playDealerTurn(BlackjackGame blackjackGame) {
        if (blackjackGame.isDealerShouldDrawCard()) {
            outputView.printDealerOneMoreCardMessage();
        }
        blackjackGame.processDealerTurn();
    }

}
