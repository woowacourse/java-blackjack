package controller;

import static view.CardName.getHandStatusAsString;

import controller.dto.gamer.GamerHandStatus;
import domain.BetAmount;
import domain.Dealer;
import domain.GameHost;
import domain.Gamer;
import domain.Gamers;
import view.CardName;
import view.InputView;
import view.OutputView;

public class GameHostController {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private final GameHost gameHost = new GameHost(inputView.enterPlayerNames());

    public BetAmount getBetAmounts() {
        Gamers gamers = gameHost.findPlayingGamers();
        BetAmount betAmount = new BetAmount();
        for (Gamer gamer : gamers.listOf()) {
            betAmount.saveAmount(gamer, inputView.enterGamerBettingAmounts(gamer.getName()));
        }
        return betAmount;
    }

    public GameHost startGame() {
        gameHost.readyGame();
        Dealer dealer = gameHost.findPlayingDealer();
        Gamers gamers = gameHost.findPlayingGamers();

        printInitiateGameResult(dealer, gamers);
        playGame(gamers);
        printDealerDrawMessage(dealer);

        return gameHost;
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

    private void printDealerDrawMessage(final Dealer dealer) {
        int count = gameHost.cardDrawCountOfDealer(dealer);
        outputView.printDealerPickMessage(count);
    }
}
