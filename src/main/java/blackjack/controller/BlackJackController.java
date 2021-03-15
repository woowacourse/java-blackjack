package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.money.Money;
import blackjack.domain.user.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    public void play() {
        List<String> requestPlayers = InputView.requestPlayers();
        List<Money> requestCapital = InputView.requestCapital(requestPlayers);
        BlackJackGame blackJackGame = new BlackJackGame(requestPlayers, requestCapital);
        OutputView.showInitiate(blackJackGame);
        process(blackJackGame);
        terminateGame(blackJackGame);
    }

    private void process(BlackJackGame blackJackGame) {
        processPlayers(blackJackGame);
        processDealer(blackJackGame);
    }

    private void processPlayers(BlackJackGame blackJackGame) {
        for (int i = 0; i < blackJackGame.playersSize(); i++) {
            processPlayer(blackJackGame, i);
        }
    }

    private void processPlayer(BlackJackGame blackJackGame, int playerIndex) {
        while (blackJackGame.isAvailablePlayerDraw(playerIndex) &&
            isPlayerYes(blackJackGame, playerIndex)) {
            blackJackGame.drawOnePlayer(playerIndex);
            OutputView.showPlayerCard(blackJackGame, playerIndex);
        }
    }

    private boolean isPlayerYes(BlackJackGame blackJackGame, int playerIndex) {
        return InputView.requestMoreDraw(blackJackGame, playerIndex);
    }

    private void processDealer(BlackJackGame blackJackGame) {
        while (blackJackGame.isAvailableDealerTurn()) {
            blackJackGame.dealerTurn();
            OutputView.showDealerDraw();
        }
    }

    private void terminateGame(BlackJackGame blackJackGame) {
        GameResult gameResult = blackJackGame.getDealerResult();
        OutputView.showScoreResult(blackJackGame);
        OutputView.showMoneyStatue(blackJackGame);
    }
}
