package controller;

import static view.CardName.getHandStatusAsString;

import controller.dto.GameResult;
import controller.dto.PlayerResult;
import controller.dto.dealer.DealerHand;
import controller.dto.dealer.DealerScore;
import controller.dto.gamer.GamerHand;
import controller.dto.gamer.GamerScore;
import domain.GameHost;
import domain.GameRule;
import domain.Gamer;
import domain.Gamers;
import domain.Hand;
import domain.constants.ProfitRate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class GameRuleController {
    private final GameHost gameHost;

    public GameRuleController(final GameHost gameHost) {
        this.gameHost = gameHost;
    }

    public void betting() {
        Gamers gamers = gameHost.findPlayingGamers();
        Map<Gamer, Integer> bettingAmounts = new HashMap<>();

        for (Gamer gamer : gamers.listOf()) {
            bettingAmounts.put(gamer, InputView.enterGamerBettingAmounts(gamer.getName()));
        }
        gamers.saveBettingAmounts(bettingAmounts);
    }

    public void printResult() {
        Gamers gamers = gameHost.findPlayingGamers();

        printCardStatusAndScores();
        OutputView.printGameResult(getResultsOfGame(gamers));
    }

    private void printCardStatusAndScores() {
        OutputView.printHandStatusWithScore(
                getCurrentDealerHandScore(),
                getCurrentGamerHandScore(),
                gameHost.gamerNames()
        );
    }

    private DealerScore getCurrentDealerHandScore() {
        DealerHand dealerHand = new DealerHand(getHandStatusAsString(gameHost.dealerHand()));
        return new DealerScore(dealerHand, gameHost.dealerScore());
    }

    private List<GamerScore> getCurrentGamerHandScore() {
        List<GamerHand> gamerHandStatuses = getGamerHandStatuses();
        List<Integer> scores = gameHost.gamerScores();

        List<GamerScore> gamerScores = new ArrayList<>();
        for (int i = 0; i < gamerHandStatuses.size(); i++) {
            gamerScores.add(new GamerScore(gamerHandStatuses.get(i), scores.get(i)));
        }

        return gamerScores;
    }

    private List<GamerHand> getGamerHandStatuses() {
        List<GamerHand> gamerHandStatuses = new ArrayList<>();
        List<String> gamerNames = gameHost.gamerNames();
        List<Hand> gamerHands = gameHost.gamerHands();

        for (int i = 0; i < gamerNames.size(); i++) {
            gamerHandStatuses.add(new GamerHand(gamerNames.get(i), getHandStatusAsString(gamerHands.get(i))));
        }
        return gamerHandStatuses;
    }

    private GameResult getResultsOfGame(final Gamers gamers) {
        GameRule gameRule = new GameRule(gameHost.findPlayingDealer(), gamers);
        List<ProfitRate> results = gameRule.judge();

        return new GameResult(createPlayerResults(gamers, results));
    }

    private List<PlayerResult> createPlayerResults(final Gamers gamers, final List<ProfitRate> results) {
        List<PlayerResult> playerResults = new ArrayList<>();
        List<String> names = gamers.names();
        List<Integer> betAmounts = gamers.betAmounts();

        for (int i = 0; i < names.size(); i++) {
            ProfitRate profitRate = results.get(i);
            playerResults.add(new PlayerResult(names.get(i), profitRate.getProfit(betAmounts.get(i))));
        }
        return playerResults;
    }
}
