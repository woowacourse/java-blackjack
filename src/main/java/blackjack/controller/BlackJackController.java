package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.user.DealerResult;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void play() {
        BlackJackGame blackJackGame = new BlackJackGame(InputView.requestPlayers());
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
        Player player = blackJackGame.getPlayer(playerIndex);
        while(player.isAvailableDraw() && isYes(player)) {
            blackJackGame.drawOnePlayer(playerIndex);
            OutputView.showPlayerCard(player);
        }
    }

    private boolean isYes(Player player) {
        return InputView.requestMoreDraw(player.getName());
    }

    private void processDealer(BlackJackGame blackJackGame) {
        while(blackJackGame.isAvailableDealerTurn()) {
            blackJackGame.dealerTurn();
            OutputView.showDealerDraw();
        }
    }

    private void terminateGame(BlackJackGame blackJackGame) {
        DealerResult dealerResult = blackJackGame.getDealerResult();
        OutputView.showScoreResult(blackJackGame);
        OutputView.showDealerTable(dealerResult);
        OutputView.showIndividualTable(blackJackGame);
    }
}
