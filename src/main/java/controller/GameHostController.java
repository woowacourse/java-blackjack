package controller;

import static view.CardName.getDealerHandWithHiddenCard;
import static view.CardName.getGamerHandStatus;
import static view.CardName.getHandStatusAsString;

import controller.dto.dealer.DealerHand;
import controller.dto.gamer.GamerHand;
import domain.GameHost;
import domain.Gamer;
import domain.Gamers;
import java.util.List;
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
        printInitiateGameResult();
        startCardDraw();
        printDealerDrawMessage();
    }

    private void printInitiateGameResult() {
        List<String> gamerNames = gameHost.getGamerNames();
        outputView.printNoticeAfterStartGame(gamerNames);

        DealerHand dealerHandWithHiddenCard = getDealerHandWithHiddenCard(gameHost.getDealerHand());
        outputView.printDealerStatus(dealerHandWithHiddenCard);

        List<GamerHand> gamerHandStatus = getGamerHandStatus(gamerNames, gameHost.getGamerHands());
        outputView.printPlayerStatus(gamerNames, gamerHandStatus);
    }

    private void startCardDraw() {
        Gamers gamers = gameHost.findPlayingGamers();
        for (Gamer gamer : gamers.listOf()) {
            makeGamerToDrawCard(gamer);
        }
    }

    private void makeGamerToDrawCard(final Gamer gamer) {
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

    private void printDealerDrawMessage() {
        int count = gameHost.cardDrawCountOfDealer();
        outputView.printDealerPickMessage(count);
    }
}
