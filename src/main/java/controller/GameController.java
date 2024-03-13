package controller;

import static view.CardName.getHandStatusAsString;

import controller.dto.GameResult;
import controller.dto.PlayerResult;
import controller.dto.dealer.DealerHandScore;
import controller.dto.dealer.DealerHandStatus;
import controller.dto.gamer.GamerHandScore;
import controller.dto.gamer.GamerHandStatus;
import domain.Dealer;
import domain.GameHost;
import domain.GameRule;
import domain.Gamer;
import domain.Gamers;
import java.util.ArrayList;
import java.util.List;
import view.CardName;
import view.InputView;
import view.OutputView;

public class GameController {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private final GameHost gameHost = new GameHost(inputView.enterPlayerNames());

    public void startGame() {
        gameHost.readyGame();
        Dealer dealer = gameHost.findPlayingDealer();
        Gamers gamers = gameHost.findPlayingGamers();

        printInitiateGameResult(dealer, gamers);
        playGame(gamers);
        printDealerDrawMessage(dealer);
        printResult(dealer, gamers);
    }

    private void printInitiateGameResult(final Dealer dealer, final Gamers gamers) {
        outputView.printNoticeAfterStartGame(gamers.getNames());
        outputView.printDealerStatusAfterStartGame(CardName.getDealerHandWithHiddenCard(dealer.getHand()));
        outputView.printPlayerStatusAfterStartGame(gamers.getNames(), CardName.getGamerHandStatus(gamers));
    }

    private void playGame(final Gamers gamers) {
        for (Gamer gamer : gamers.listOf()) {
            giveCardToGamer(gamer);
        }
    }

    private void giveCardToGamer(final Gamer gamer) {
        GamerHandStatus currentHand = new GamerHandStatus(gamer.getName(), getHandStatusAsString(gamer.getHand()));
        GameCommand command = inputCommand(gamer.getName());

        while (command.isHit()) {
            gameHost.drawOneCardToGamer(gamer);
            currentHand = getStatusAfterDraw(gamer);
            command = getCommandAfterDraw(gamer);
        }
        outputView.printCardStatus(gamer.getName(), currentHand);
    }

    private GamerHandStatus getStatusAfterDraw(final Gamer gamer) {
        return new GamerHandStatus(gamer.getName(), getHandStatusAsString(gamer.getHand()));
    }

    private GameCommand getCommandAfterDraw(final Gamer gamer) {
        if (gamer.isNotAbleToDrawCard()) {
            return GameCommand.STAND;
        }
        outputView.printCardStatus(gamer.getName(),
                new GamerHandStatus(gamer.getName(), getHandStatusAsString(gamer.getHand())));
        return inputCommand(gamer.getName());
    }

    private GameCommand inputCommand(final String name) {
        return GameCommand.valueOf(inputView.decideToGetMoreCard(name));
    }

    public void printDealerDrawMessage(final Dealer dealer) {
        int count = gameHost.cardDrawCountOfDealer(dealer);
        outputView.printDealerPickMessage(count);
    }

    public void printResult(final Dealer dealer, final Gamers gamers) {
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
        List<Boolean> results = gameRule.judge();
        List<String> names = gamers.getNames();

        return new GameResult(createPlayerResults(names, results));
    }

    private List<PlayerResult> createPlayerResults(final List<String> names, final List<Boolean> results) {
        List<PlayerResult> playerResults = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            playerResults.add(new PlayerResult(names.get(i), results.get(i)));
        }
        return playerResults;
    }
}
