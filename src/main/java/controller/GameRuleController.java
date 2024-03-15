package controller;

import static view.CardName.getHandStatusAsString;

import controller.dto.GameResult;
import controller.dto.PlayerResult;
import controller.dto.dealer.DealerHand;
import controller.dto.dealer.DealerScore;
import controller.dto.gamer.GamerHand;
import controller.dto.gamer.GamerScore;
import domain.BetAmount;
import domain.GameHost;
import domain.GameRule;
import domain.Gamer;
import domain.Gamers;
import domain.constants.ProfitRate;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class GameRuleController {
    private final BetAmount betAmount = new BetAmount();
    private final GameHost gameHost;

    public GameRuleController(final GameHost gameHost) {
        this.gameHost = gameHost;
    }

    public void betting() {
        Gamers gamers = gameHost.findPlayingGamers();
        for (Gamer gamer : gamers.listOf()) {
            betAmount.saveAmount(gamer, InputView.enterGamerBettingAmounts(gamer.getName()));
        }
    }

    public void printResult() {
        Gamers gamers = gameHost.findPlayingGamers();

        printCardStatusAndScores(gamers);
        OutputView.printGameResult(getResultsOfGame(gamers));
    }

    private void printCardStatusAndScores(final Gamers gamers) {
        OutputView.printHandStatusWithScore(
                getCurrentDealerHandScore(),
                getCurrentGamerHandScore(gamers),
                gamers.getNames()
        );
    }

    private DealerScore getCurrentDealerHandScore() {
        DealerHand dealerHand = new DealerHand(getHandStatusAsString(gameHost.getDealerHand()));
        return new DealerScore(dealerHand, gameHost.getDealerScore());
    }

    private List<GamerScore> getCurrentGamerHandScore(final Gamers gamers) {
        List<GamerHand> gamerHandStatuses = getGamerHandStatuses(gamers);
        List<Integer> scores = getGamerResultScore(gamers);

        List<GamerScore> gamerScores = new ArrayList<>();
        for (int i = 0; i < gamerHandStatuses.size(); i++) {
            gamerScores.add(new GamerScore(gamerHandStatuses.get(i), scores.get(i)));
        }

        return gamerScores;
    }

    private List<Integer> getGamerResultScore(final Gamers gamers) {
        List<Integer> scores = new ArrayList<>();
        for (Gamer gamer : gamers.listOf()) {
            scores.add(gamer.calculateResultScore());
        }
        return scores;
    }

    private List<GamerHand> getGamerHandStatuses(final Gamers gamers) {
        List<GamerHand> gamerHandStatuses = new ArrayList<>();

        for (Gamer gamer : gamers.listOf()) {
            gamerHandStatuses.add(
                    new GamerHand(gamer.getName(), getHandStatusAsString(gamer.getHand()))
            );
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
        List<Gamer> gamerList = gamers.listOf();
        List<String> names = gamers.getNames();
        List<Integer> betAmounts = getGamerBetAmounts(gamerList);

        for (int i = 0; i < names.size(); i++) {
            ProfitRate profitRate = results.get(i);
            playerResults.add(new PlayerResult(names.get(i), profitRate.getProfit(betAmounts.get(i))));
        }
        return playerResults;
    }

    private List<Integer> getGamerBetAmounts(final List<Gamer> gamerList) {
        List<Integer> betAmounts = new ArrayList<>();
        for (Gamer gamer : gamerList) {
            betAmounts.add(betAmount.getAmount(gamer));
        }
        return betAmounts;
    }
}
