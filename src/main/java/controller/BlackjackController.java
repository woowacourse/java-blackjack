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
        List<String> playerNames = InputView.readNicknames();
        BlackjackGame game = new BlackjackGame(playerNames);

        game.startGame();
        OutputView.printInitialCards(game.getDealer(), game.getPlayers());

        for (Player player : game.getPlayers().getPlayers()) {
            while (player.canReceiveCard()) {
                boolean drawOneMore = InputView.readDrawOneMore(player.getNickname());
                if (!drawOneMore) {
                    break;
                }
                game.runPlayerTurn(player);
                OutputView.printPlayerCards(player);
            }
        }

        while (game.getDealer().canReceiveCard()) {
            OutputView.printDealerReceivedCard();
            game.runDealerTurn();
        }

        OutputView.printAllCardAndScore(game.getPlayers(), game.getDealer());
        Map<Player, GameResult> playerGameResults = game.calculatePlayerGameResult();
        Map<GameResult, Integer> dealerResults = game.calculateDealerGameResult(playerGameResults);
        OutputView.printResult(dealerResults, playerGameResults);
    }
}
