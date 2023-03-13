package blackjack.controller;

import static blackjack.controller.Repeater.repeatUntilNoException;

import blackjack.domain.DeckFactory;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.ResultOfGame;
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

    private static void initInitialCards(final BlackJackGame blackJackGame) {
        blackJackGame.distributeInitialCards();
    }

    public void generate(final DeckFactory deckFactory) {
        final BlackJackGame blackJackGame = createBlackJackGame(deckFactory);

        initInitialCards(blackJackGame);
        printInitialCard(blackJackGame);
        play(blackJackGame);
        printResult(blackJackGame);
    }

    private BlackJackGame createBlackJackGame(final DeckFactory deckFactory) {
        return repeatUntilNoException(
                () -> BlackJackGame.of(inputView.inputPlayerNames(), deckFactory.generate()), outputView::printError);
    }

    private void printInitialCard(final BlackJackGame blackJackGame) {
        outputView.printInitialCards(blackJackGame.findDealerInitialCard(),
                blackJackGame.findPlayerNameAndCards());
    }

    private void play(final BlackJackGame blackJackGame) {
        drawPlayersCards(blackJackGame);
        drawDealerCards(blackJackGame);
    }

    private void drawPlayersCards(final BlackJackGame blackJackGame) {
        for (final String playerName : blackJackGame.findPlayerNames()) {
            drawPlayerCard(playerName, blackJackGame);
        }
    }

    private DrawCommand inputDrawCommand(final String playerName) {
        return repeatUntilNoException(
                () -> inputView.inputCommand(playerName), outputView::printError);
    }

    private void drawPlayerCard(final String playerName, final BlackJackGame blackJackGame) {
        DrawCommand playerInput = DrawCommand.DRAW;
        while (blackJackGame.isPlayerDrawable(playerName) && playerInput == DrawCommand.DRAW) {
            playerInput = inputDrawCommand(playerName);
            blackJackGame.drawCardOfPlayerByName(playerName, playerInput);
            outputView.printCurrentCardsOfPlayer(playerName, blackJackGame.findCardsOfPlayerByName(playerName));
        }
    }

    private void drawDealerCards(final BlackJackGame blackJackGame) {
        IntStream.range(0, blackJackGame.findDealerDrawCount())
                .forEach(ignored -> outputView.printDealerCardDrawMessage(blackJackGame.findDealerDrawPoint()));
    }

    private void printResult(final BlackJackGame blackJackGame) {
        printFinalStatusOfParticipants(blackJackGame);
        printResultOfGame(blackJackGame);
    }

    private void printFinalStatusOfParticipants(final BlackJackGame blackJackGame) {
        outputView.printFinalStatusOfDealer(blackJackGame.findDealerCard(), blackJackGame.findDealerScore()
                .getValue());
        outputView.printFinalStatusOfPlayers(blackJackGame.findPlayerStatusByName());
    }

    private void printResultOfGame(final BlackJackGame blackJackGame) {
        final ResultOfGame result = blackJackGame.findResultOfGame();
        outputView.printFinalResult(result.getDealerResult(), result.getPlayerResult());
    }

}
