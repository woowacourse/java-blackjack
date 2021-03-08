package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Map;

public class Casino {

    public void blackJack() {
        Game game = initializeGame();

        printInitialCardInformation(game);
        doPlayersTurn(game);
        doDealerTurn(game);
        printGameResult(game);
    }

    private void printInitialCardInformation(Game game) {
        OutputView.printSetup(game.getDealer(), game.getPlayers());
    }

    private Game initializeGame() {
        Players players = new Players(InputView.inputPlayerNames());
        return Game.of(players);
    }

    private void doPlayersTurn(Game game) {
        for (Player player : game.getPlayers()) {
            doEachPlayerTurn(game, player);
        }
    }

    private void doEachPlayerTurn(Game game, Player player) {
        while (game.isPlayerDrawable(player) && InputView.inputYesOrNo(player)) {
            game.giveCardToPlayer(player);
            OutputView.printCardInfo(player);
        }
    }

    private void doDealerTurn(Game game) {
        while (game.isDealerDrawable()) {
            game.giveCardToDealer();
            OutputView.printDealerDraw();
        }
        OutputView.printDealerNoMoreDraw();
    }

    private void printGameResult(Game game) {
        Map<String, String> playersGameResult = game.getPlayersGameResult();
        String dealerGameResult = game.getDealerGameResult(playersGameResult);

        OutputView.printDealerAndPlayersCardInfoWithScore(game.getDealer(), game.getPlayers());
        OutputView.printPlayerGameResult(dealerGameResult, playersGameResult);
    }
}