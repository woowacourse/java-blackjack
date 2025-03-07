package controller;

import java.util.List;
import java.util.Map;
import model.BlackjackGame;
import model.GameResult;
import model.participant.Player;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        BlackjackGame game = initialize();
        processPlayersTurn(game);
        processDealerTurn(game);
        processGameResult(game);
    }

    private BlackjackGame initialize() {
        List<String> playerNames = InputView.readNicknames();
        BlackjackGame game = new BlackjackGame(playerNames);

        game.startGame();
        OutputView.printInitialCards(game.getDealer(), game.getPlayers());
        return game;
    }

    private void processPlayersTurn(BlackjackGame game) {
        for (Player player : game.getPlayers().getPlayers()) {
            processPlayerTurn(game, player);
        }
    }

    private void processPlayerTurn(BlackjackGame game, Player player) {
        while (player.canReceiveCard()) {
            boolean drawOneMore = InputView.readDrawOneMore(player.getNickname());
            if (!drawOneMore) {
                break;
            }
            game.runPlayerTurn(player);
            OutputView.printPlayerCards(player);
        }
    }

    private void processDealerTurn(BlackjackGame game) {
        while (game.getDealer().canReceiveCard()) {
            game.runDealerTurn();
            OutputView.printDealerReceivedCard();
        }
    }

    private void processGameResult(BlackjackGame game) {
        OutputView.printAllCardAndScore(game.getPlayers(), game.getDealer());
        Map<Player, GameResult> playerGameResults = game.calculatePlayerGameResult();
        Map<GameResult, Integer> dealerResults = game.calculateDealerGameResult(playerGameResults);
        OutputView.printResult(dealerResults, playerGameResults);
    }
}
