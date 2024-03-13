package controller;

import controller.dto.GameResult;
import controller.dto.PlayerResult;
import controller.dto.dealer.DealerHandScore;
import controller.dto.dealer.DealerHandStatus;
import controller.dto.gamer.GamerHandScore;
import controller.dto.gamer.GamerHandStatus;
import domain.BetAmount;
import domain.Dealer;
import domain.GameHost;
import domain.GameRule;
import domain.Gamer;
import domain.Gamers;
import domain.constants.ProfitRate;
import java.util.ArrayList;
import java.util.List;
import view.CardName;
import view.OutputView;

public class GameRuleController {
    private static final OutputView outputView = new OutputView();

    private final BetAmount betAmount;
    private final GameHost gameHost;

    public GameRuleController(final BetAmount betAmount, final GameHost gamerHost) {
        this.betAmount = betAmount;
        this.gameHost = gamerHost;
    }

    public void printResult() {
        Dealer dealer = gameHost.findPlayingDealer();
        Gamers gamers = gameHost.findPlayingGamers();

        printCardStatusAndScores(dealer, gamers);
        outputView.printGameResult(getResultsOfGame(dealer, gamers));
    }

    private void printCardStatusAndScores(final Dealer dealer, final Gamers gamers) {
        outputView.printHandStatusWithScore(
                getCurrentDealerHandScore(dealer),
                getCurrentGamerHandScore(gamers),
                gamers.getNames()
        );
    }

    private DealerHandScore getCurrentDealerHandScore(final Dealer dealer) {
        DealerHandStatus dealerHand = new DealerHandStatus(CardName.getHandStatusAsString(dealer.getHand()));
        return new DealerHandScore(dealerHand, dealer.calculateResultScore());
    }

    private List<GamerHandScore> getCurrentGamerHandScore(final Gamers gamers) {
        List<GamerHandStatus> gamerHandStatuses = getGamerHandStatuses(gamers);
        List<Integer> scores = getGamerResultScore(gamers);

        List<GamerHandScore> gamerHandScores = new ArrayList<>();
        for (int i = 0; i < gamerHandStatuses.size(); i++) {
            gamerHandScores.add(new GamerHandScore(gamerHandStatuses.get(i), scores.get(i)));
        }

        return gamerHandScores;
    }

    private List<Integer> getGamerResultScore(final Gamers gamers) {
        List<Integer> scores = new ArrayList<>();
        for (Gamer gamer : gamers.listOf()) {
            scores.add(gamer.calculateResultScore());
        }
        return scores;
    }

    private List<GamerHandStatus> getGamerHandStatuses(final Gamers gamers) {
        List<GamerHandStatus> gamerHandStatuses = new ArrayList<>();

        for (Gamer gamer : gamers.listOf()) {
            gamerHandStatuses.add(
                    new GamerHandStatus(gamer.getName(), CardName.getHandStatusAsString(gamer.getHand()))
            );
        }
        return gamerHandStatuses;
    }

    private GameResult getResultsOfGame(final Dealer dealer, final Gamers gamers) {
        GameRule gameRule = new GameRule(dealer, gamers);
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
