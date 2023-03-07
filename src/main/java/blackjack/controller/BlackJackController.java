package blackjack.controller;

import static blackjack.util.Repeater.repeatUntilNoException;

import blackjack.domain.card.DeckFactory;
import blackjack.service.BlackJackGame;
import blackjack.view.DrawCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play(final DeckFactory deckFactory) {
        final BlackJackGame blackJackGame = repeatUntilNoException(
                () -> BlackJackGame.of(
                        inputPlayerNames(),
                        deckFactory),
                outputView::printError);

        blackJackGame.distributeInitialCard();
        outputView.printInitialCards(blackJackGame.getInitialCardResponse());

        drawPlayersCards(blackJackGame);
        drawDealerCards(blackJackGame);

        outputView.printFinalStatusOfDealer(blackJackGame.getDealerScoreResponse());
        outputView.printFinalStatusOfPlayers(blackJackGame.getPlayersCardsResponse());
        outputView.printFinalResult(blackJackGame.createFinalResultResponse());
    }

    private List<String> inputPlayerNames() {
        return inputView.inputPlayerNames();
    }

    private void drawPlayersCards(final BlackJackGame blackJackGame) {
        for (final String playerName : blackJackGame.getPlayerNames()) {
            drawPlayerCard(playerName, blackJackGame);
        }
    }

    private void drawPlayerCard(final String playerName, final BlackJackGame blackJackGame) {
        DrawCommand playerInput = DrawCommand.DRAW;
        while (blackJackGame.isPlayerDrawable(playerName) && playerInput != DrawCommand.STAY) {
            playerInput = repeatUntilNoException(
                    () -> inputView.inputCommand(playerName), outputView::printError);
            drawCard(playerName, blackJackGame, playerInput);
            printPlayerResult(playerName, blackJackGame);
        }
    }

    private void drawCard(final String playerName, final BlackJackGame blackJackGame,
            final DrawCommand playerInput) {
        if (playerInput == DrawCommand.DRAW) {
            blackJackGame.drawPlayerCard(playerName);
        }
    }

    private void printPlayerResult(final String playerName, final BlackJackGame blackJackGame) {
        outputView.printCardStatusOfPlayer(blackJackGame.getPlayerCardsResponse(playerName));
    }

    private void drawDealerCards(final BlackJackGame blackJackGame) {
        while (blackJackGame.isDealerDrawable()) {
            blackJackGame.drawDealerCard();
            outputView.printDealerCardDrawMessage();
        }
    }

}
