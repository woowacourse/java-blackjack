package blackjack.controller;

import static blackjack.controller.Repeater.repeatUntilNoException;

import blackjack.domain.DeckFactory;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participants.Money;
import blackjack.view.DrawCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.stream.IntStream;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void generate(final DeckFactory deckFactory) {
        final BlackjackGame blackjackGame = createBlackJackGame(deckFactory);

        startGame(blackjackGame);
        printInitialCard(blackjackGame);
        playGame(blackjackGame);
        printResult(blackjackGame);
    }

    private BlackjackGame createBlackJackGame(final DeckFactory deckFactory) {
        return repeatUntilNoException(
                () -> BlackjackGame.of(inputView.inputPlayerNames(), deckFactory.generate()), outputView::printError);
    }

    private void startGame(final BlackjackGame blackjackGame) {
        makePlayersPlaceWagers(blackjackGame);
        blackjackGame.distributeInitialCards();
    }

    private void makePlayersPlaceWagers(final BlackjackGame blackjackGame) {
        for (final String playerName : blackjackGame.findPlayerNames()) {
            blackjackGame.placePlayerBets(playerName, inputBettingMoney(playerName));
        }
    }

    private Money inputBettingMoney(final String playerName) {
        return repeatUntilNoException(
                () -> new Money(inputView.inputBettingMoney(playerName)), outputView::printError);
    }

    private void printInitialCard(final BlackjackGame blackjackGame) {
        outputView.printInitialCards(blackjackGame.findDealerInitialCard(),
                blackjackGame.findPlayerNameToCards());
    }

    private void playGame(final BlackjackGame blackjackGame) {
        drawPlayersCards(blackjackGame);
        drawDealerCards(blackjackGame);
    }

    private void drawPlayersCards(final BlackjackGame blackjackGame) {
        for (final String playerName : blackjackGame.findPlayerNames()) {
            drawPlayerCard(playerName, blackjackGame);
        }
    }

    private DrawCommand inputDrawCommand(final String playerName) {
        return repeatUntilNoException(
                () -> inputView.inputCommand(playerName), outputView::printError);
    }

    private void drawPlayerCard(final String playerName, final BlackjackGame blackjackGame) {
        DrawCommand drawCommand = DrawCommand.DRAW;
        while (blackjackGame.isPlayerDrawable(playerName) && drawCommand == DrawCommand.DRAW) {
            drawCommand = inputDrawCommand(playerName);
            blackjackGame.drawCardOfPlayerByName(playerName, drawCommand);
            outputView.printCurrentCardsOfPlayer(playerName, blackjackGame.findCardsOfPlayerByName(playerName));
        }
    }

    private void drawDealerCards(final BlackjackGame blackjackGame) {
        IntStream.range(0, blackjackGame.findDealerDrawCount())
                .forEach(ignored -> outputView.printDealerCardDrawMessage(blackjackGame.findDealerDrawPoint()));
    }

    private void printResult(final BlackjackGame blackjackGame) {
        printFinalStatusOfParticipants(blackjackGame);
    }

    private void printFinalStatusOfParticipants(final BlackjackGame blackjackGame) {
        outputView.printFinalStatusOfDealer(blackjackGame.findDealerCard(), blackjackGame.findDealerScore()
                .getValue());

        for (final String playerName : blackjackGame.findPlayerNames()) {
            outputView.printFinalStatusOfPlayer(playerName,
                    blackjackGame.findCardsOfPlayerByName(playerName),
                    blackjackGame.findScoreOfPlayerByName(playerName)
                            .getValue());
        }
    }


}
