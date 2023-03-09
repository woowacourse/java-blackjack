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
        outputView.printInitialCards(blackJackGame.getDealerFirstCard(), blackJackGame.getPlayerCards());

        for (final String playerName : blackJackGame.getPlayerNames()) {
            DrawCommand playerChoice = DrawCommand.DRAW;
            while (blackJackGame.isPlayerDrawable(playerName) && playerChoice != DrawCommand.STAY) {
                playerChoice = inputPlayerChoice(playerName);
                if (playerChoice == DrawCommand.DRAW) {
                    blackJackGame.drawPlayerCard(playerName);
                }
                outputView.printCardStatusOfPlayer(playerName, blackJackGame.getPlayerCardsResponse(playerName));
            }
        }

        while (blackJackGame.isDealerDrawable()) {
            blackJackGame.drawDealerCard();
            outputView.printDealerCardDrawMessage();
        }

        outputView.printFinalStatusOfDealer(blackJackGame.getDealerScore(), blackJackGame.getDealerCardsResponse());
        outputView.printFinalStatusOfPlayers(blackJackGame.getPlayerCards(), blackJackGame.getPlayerScores());
        outputView.printFinalDealerResult(blackJackGame.getDealerResult());
        outputView.printFinalPlayersResult(blackJackGame.generatePlayersResult());
    }

    private DrawCommand inputPlayerChoice(final String playerName) {
        return repeatUntilNoException(
                () -> inputView.inputCommand(playerName), outputView::printError);
    }

    private List<String> inputPlayerNames() {
        return inputView.inputPlayerNames();
    }

}
