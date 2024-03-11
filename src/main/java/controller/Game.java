package controller;

import controller.dto.GameResult;
import controller.dto.PlayerResult;
import controller.dto.dealer.DealerHandScore;
import controller.dto.dealer.DealerHandStatus;
import controller.dto.gamer.GamerHandScore;
import controller.dto.gamer.GamerHandStatus;
import domain.Dealer;
import domain.GameRule;
import domain.Gamer;
import domain.Gamers;
import domain.Player;
import java.util.ArrayList;
import java.util.List;
import view.CardName;
import view.InputView;
import view.OutputView;

public class Game {
    private final InputView inputView;
    private final OutputView outputView;

    public Game(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Round round = start();
        startRound(round);
        giveCardToDealer(round);

        outputView.printHandStatusWithScore(
                getCurrentDealerHandScore(round),
                getCurrentGamerHandScore(round),
                round.getGamerNames()
        );
        outputView.printGameResult(getResults(round));
    }

    private Round start() {
        List<String> names = inputView.enterPlayerNames();
        Dealer dealer = new Dealer();

        Round round = new Round(dealer, names);
        Gamers gamers = round.getGamers();
        round.initiateGameCondition();

        outputView.printNoticeAfterStartGame(names);
        outputView.printDealerStatusAfterStartGame(CardName.getDealerHandStatusWithHiddenCard(dealer.getHand()));
        outputView.printPlayerStatusAfterStartGame(names, CardName.getGamerHandStatus(gamers));

        return round;
    }

    private List<Integer> getGamerResultScore(final Round round) {
        List<Integer> scores = new ArrayList<>();
        Gamers gamers = round.getGamers();
        for (Player player : gamers.listOf()) {
            scores.add(player.calculateResultScore());
        }
        return scores;
    }

    private int getDealerResultScore(final Round round) {
        return getDealer(round).calculateResultScore();
    }

    private Dealer getDealer(final Round round) {
        return round.getDealer();
    }

    private DealerHandScore getCurrentDealerHandScore(final Round round) {
        Dealer dealer = round.getDealer();

        DealerHandStatus dealerHand = new DealerHandStatus(CardName.getHandStatusAsString(dealer.getHand()));
        return new DealerHandScore(dealerHand, getDealerResultScore(round));
    }

    private List<GamerHandScore> getCurrentGamerHandScore(final Round round) {
        List<GamerHandStatus> gamerHandStatuses = getPlayerHandStatuses(round);
        List<Integer> scores = getGamerResultScore(round);

        List<GamerHandScore> gamerHandScores = new ArrayList<>();
        for (int i = 0; i < gamerHandStatuses.size(); i++) {
            gamerHandScores.add(new GamerHandScore(gamerHandStatuses.get(i), scores.get(i)));
        }

        return gamerHandScores;
    }

    private List<GamerHandStatus> getPlayerHandStatuses(final Round round) {
        List<GamerHandStatus> gamerHandStatuses = new ArrayList<>();
        Gamers gamers = round.getGamers();
        for (Gamer gamer : gamers.listOf()) {
            gamerHandStatuses.add(
                    new GamerHandStatus(gamer.getName(), CardName.getHandStatusAsString(gamer.getHand()))
            );
        }
        return gamerHandStatuses;
    }

    private void startRound(final Round round) {
        List<String> names = round.getGamerNames();
        for (String name : names) {
            round.giveCardToGamer(name, outputView, inputView);
        }
    }

    private void giveCardToDealer(final Round round) {
        int count = round.giveCardsToDealer();
        outputView.printDealerPickMessage(count);
    }

    public GameResult getResults(final Round round) {
        GameRule rule = new GameRule(round.getDealer(), round.getGamers());
        List<Boolean> results = rule.judge();
        List<String> names = round.getGamerNames();

        List<PlayerResult> playerResults = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            playerResults.add(new PlayerResult(names.get(i), results.get(i)));
        }
        return new GameResult(playerResults);
    }
}
