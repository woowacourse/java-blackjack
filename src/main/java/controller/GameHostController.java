package controller;

import static view.CardName.getHandStatusAsString;

import controller.dto.gamer.GamerHand;
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

    public GameHost initGame() {
        gameHost.readyGame();
        return gameHost;
    }

    public void playGame() {
        Dealer dealer = gameHost.findPlayingDealer();
        Gamers gamers = gameHost.findPlayingGamers();

        printInitiateGameResult(dealer, gamers);
        startCardDraw(gamers);
        printDealerDrawMessage(dealer);
    }

    private void printInitiateGameResult(final Dealer dealer, final Gamers gamers) {
        outputView.printNoticeAfterStartGame(gamers.getNames());
        outputView.printDealerStatusAfterStartGame(CardName.getDealerHandWithHiddenCard(dealer.getHand()));
        outputView.printPlayerStatusAfterStartGame(gamers.getNames(), CardName.getGamerHandStatus(gamers));
    }

    private void startCardDraw(final Gamers gamers) {
        for (Gamer gamer : gamers.listOf()) {
            giveCardToGamer(gamer);
        }
    }

    private void giveCardToGamer(final Gamer gamer) {
        GamerHand currentHand = new GamerHand(gamer.getName(), getHandStatusAsString(gamer.getHand()));
        GameCommand command = inputCommand(gamer.getName());

        while (command.isHit()) {
            gameHost.drawOneCardToGamer(gamer);
            currentHand = getStatusAfterDraw(gamer);
            command = getCommandAfterDraw(gamer);
        }
        outputView.printCardStatus(gamer.getName(), currentHand);
    }

    private GamerHand getStatusAfterDraw(final Gamer gamer) {
        return new GamerHand(gamer.getName(), getHandStatusAsString(gamer.getHand()));
    }

    private GameCommand getCommandAfterDraw(final Gamer gamer) {
        if (gamer.isNotAbleToDrawCard()) {
            return GameCommand.STAND;
        }
        outputView.printCardStatus(gamer.getName(),
                new GamerHand(gamer.getName(), getHandStatusAsString(gamer.getHand())));
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
