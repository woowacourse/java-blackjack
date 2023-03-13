package blackjack.controller;

import static blackjack.controller.Repeater.repeatUntilNoException;

import blackjack.domain.DeckFactory;
import blackjack.domain.game.BlackjackGame;
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

    public void generate(final DeckFactory deckFactory) {
        final BlackjackGame blackJackGame = createBlackJackGame(deckFactory);

        initInitialCards(blackJackGame);
        printInitialCard(blackJackGame);
        play(blackJackGame);
        printResult(blackJackGame);
    }

    private BlackjackGame createBlackJackGame(final DeckFactory deckFactory) {
        return repeatUntilNoException(
                () -> BlackjackGame.of(inputView.inputPlayerNames(), deckFactory.generate()), outputView::printError);
    }

    private void initInitialCards(final BlackjackGame blackJackGame) {
        blackJackGame.distributeInitialCards();
    }

    private void printInitialCard(final BlackjackGame blackJackGame) {
        outputView.printInitialCards(blackJackGame.findDealerInitialCard(),
                blackJackGame.findPlayerNameToCards());
    }

    private void play(final BlackjackGame blackJackGame) {
        drawPlayersCards(blackJackGame);
        drawDealerCards(blackJackGame);
    }

    private void drawPlayersCards(final BlackjackGame blackJackGame) {
        for (final String playerName : blackJackGame.findPlayerNames()) {
            drawPlayerCard(playerName, blackJackGame);
        }
    }

    private DrawCommand inputDrawCommand(final String playerName) {
        return repeatUntilNoException(
                () -> inputView.inputCommand(playerName), outputView::printError);
    }

    private void drawPlayerCard(final String playerName, final BlackjackGame blackJackGame) {
        DrawCommand drawCommand = DrawCommand.DRAW;
        while (blackJackGame.isPlayerDrawable(playerName) && drawCommand == DrawCommand.DRAW) {
            drawCommand = inputDrawCommand(playerName);
            blackJackGame.drawCardOfPlayerByName(playerName, drawCommand);
            outputView.printCurrentCardsOfPlayer(playerName, blackJackGame.findCardsOfPlayerByName(playerName));
        }
    }

    private void drawDealerCards(final BlackjackGame blackJackGame) {
        IntStream.range(0, blackJackGame.findDealerDrawCount())
                .forEach(ignored -> outputView.printDealerCardDrawMessage(blackJackGame.findDealerDrawPoint()));
    }

    private void printResult(final BlackjackGame blackJackGame) {
        printFinalStatusOfParticipants(blackJackGame);
        printResultOfGame(blackJackGame);
    }

    private void printFinalStatusOfParticipants(final BlackjackGame blackJackGame) {
        outputView.printFinalStatusOfDealer(blackJackGame.findDealerCard(), blackJackGame.findDealerScore()
                .getValue());

        for (final String playerName : blackJackGame.findPlayerNames()) {
            outputView.printFinalStatusOfPlayer(playerName,
                    blackJackGame.findCardsOfPlayerByName(playerName),
                    blackJackGame.findScoreOfPlayerByName(playerName)
                            .getValue());
        }
    }

    private void printResultOfGame(final BlackjackGame blackJackGame) {
        final ResultOfGame result = blackJackGame.findResultOfGame();
        outputView.printFinalResult(result.getDealerResult(), result.getPlayerResult());
    }

}
