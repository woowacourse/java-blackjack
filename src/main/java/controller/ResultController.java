package controller;

import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.result.GameResult;
import domain.result.Outcome;
import view.OutputView;

import java.util.EnumMap;
import java.util.Map;

public class ResultController {

    private final OutputView outputView;

    public ResultController(final OutputView outputView) {
        this.outputView = outputView;
    }

    public void run(final Dealer dealer, final Players players) {
        GameResult gameResult = new GameResult(dealer, players);
        printCardAndScore(dealer, players);
        printGameResult(gameResult);

    }

    private void printCardAndScore(final Dealer dealer, final Players players) {
        printDealerCardAndScore(dealer);
        printPlayersCardAndScore(players);
        outputView.printEmptyLine();
    }

    private void printDealerCardAndScore(final Dealer dealer) {
        outputView.printDealerCardAndScore(dealer);
    }

    private void printPlayersCardAndScore(final Players players) {
        players.getPlayers()
                .forEach(this::printPlayerCardAndScore);
    }

    private void printPlayerCardAndScore(final Player player) {
        outputView.printPlayerCardAndScore(player);
    }

    private void printGameResult(final GameResult gameResult) {
        EnumMap<Outcome, Integer> dealerResult = gameResult.makeDealerResult();
        Map<String, Outcome> playerResults = gameResult.makePlayerResults();
        outputView.printGameResult(dealerResult, playerResults);
    }
}
