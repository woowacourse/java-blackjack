package controller;

import static view.CardName.getHandStatusAsString;

import controller.dto.GameResult;
import controller.dto.gamer.GamerHandStatus;
import domain.Dealer;
import domain.Deck;
import domain.GameRule;
import domain.Gamer;
import domain.Gamers;
import java.util.List;
import java.util.Random;
import view.CardName;
import view.InputView;
import view.OutputView;

public class Game {
    private static final int DECK_SIZE = Deck.getSize();

    private final Dealer dealer = new Dealer();
    private final Gamers gamers;

    public Game(final List<String> gamerNames) {
        this.gamers = new Gamers(gamerNames.stream()
                .map(Gamer::new)
                .toList());
    }

    public void readyGame(final OutputView outputView) {
        initiateGameCondition(dealer, gamers);
        printInitiateGameResult(outputView);
    }

    private void initiateGameCondition(final Dealer dealer, final Gamers gamers) {
        dealer.drawTwoCard(getRandomCardIndex(), getRandomCardIndex());
        for (Gamer gamer : gamers.listOf()) {
            gamer.drawTwoCard(getRandomCardIndex(), getRandomCardIndex());
        }
    }

    private int getRandomCardIndex() {
        Random random = new Random();
        return random.nextInt(DECK_SIZE);
    }

    private void printInitiateGameResult(final OutputView outputView) {
        outputView.printNoticeAfterStartGame(gamers.getNames());
        outputView.printDealerStatusAfterStartGame(CardName.getDealerHandStatusWithHiddenCard(dealer.getHand()));
        outputView.printPlayerStatusAfterStartGame(gamers.getNames(), CardName.getGamerHandStatus(gamers));
    }

    public void playGame(final InputView inputView, final OutputView outputView) {
        for (Gamer gamer : gamers.listOf()) {
            giveCardToGamer(gamer, outputView, inputView);
        }
    }

    private void giveCardToGamer(final Gamer gamer, final OutputView outputView, final InputView inputView) {
        GamerHandStatus currentHand = new GamerHandStatus(gamer.getName(), getHandStatusAsString(gamer.getHand()));
        GameCommand command = inputCommand(gamer.getName(), inputView);

        while (command.isHit()) {
            currentHand = getStatusAfterDraw(gamer);
            command = getCommandAfterDraw(gamer, inputView, outputView);
        }
        outputView.printCardStatus(gamer.getName(), currentHand);
    }

    private GamerHandStatus getStatusAfterDraw(final Gamer gamer) {
        gamer.drawOneCard(getRandomCardIndex());
        return new GamerHandStatus(gamer.getName(), getHandStatusAsString(gamer.getHand()));
    }

    private GameCommand getCommandAfterDraw(final Gamer gamer, final InputView inputView, final OutputView outputView) {
        if (gamer.isNotAbleToDrawCard()) {
            return GameCommand.STAND;
        }
        outputView.printCardStatus(gamer.getName(),
                new GamerHandStatus(gamer.getName(), getHandStatusAsString(gamer.getHand())));
        return inputCommand(gamer.getName(), inputView);
    }

    private GameCommand inputCommand(final String name, final InputView inputView) {
        return GameCommand.valueOf(inputView.decideToGetMoreCard(name));
    }

    public void printDealerDrawMessage(final OutputView outputView) {
        int count = cardDrawCount(dealer);
        outputView.printDealerPickMessage(count);
    }

    private int cardDrawCount(final Dealer dealer) {
        int count = 0;
        while (dealer.isNotUpToThreshold()) {
            dealer.drawOneCard(getRandomCardIndex());
            count++;
        }
        return count;
    }

    public void printResult(final OutputView outputView) {
        printCardStatusAndScores(outputView);
        outputView.printGameResult(getResults());
    }

    private void printCardStatusAndScores(final OutputView outputView) {
        outputView.printHandStatusWithScore(
                dealer.getCurrentDealerHandScore(),
                gamers.getCurrentGamerHandScore(),
                gamers.getNames()
        );
    }

    private GameResult getResults() {
        GameRule rule = new GameRule(dealer, gamers);
        return rule.getResultsOfGame();
    }
}
