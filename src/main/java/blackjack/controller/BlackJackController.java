package blackjack.controller;

import static blackjack.controller.DomainConverter.convertCard;
import static blackjack.controller.DomainConverter.convertCards;
import static blackjack.controller.DomainConverter.convertDealerResults;
import static blackjack.controller.DomainConverter.convertPlayersCards;
import static blackjack.controller.DomainConverter.convertPlayersResult;
import static blackjack.controller.DomainConverter.getPlayerCards;
import static blackjack.util.Repeater.repeatUntilNoException;

import blackjack.domain.BlackJackRuleImpl;
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
                        deckFactory,
                        new BlackJackRuleImpl()),
                outputView::printError);

        blackJackGame.distributeInitialCard();

        outputView.printInitialCards(
                convertCard(blackJackGame.getDealerFirstCard()),
                getPlayerCards(blackJackGame.getPlayers()));

        for (final String playerName : blackJackGame.getPlayerNames()) {
            DrawCommand playerChoice = DrawCommand.DRAW;
            while (blackJackGame.isPlayerDrawable(playerName) && playerChoice != DrawCommand.STAY) {
                playerChoice = inputPlayerChoice(playerName);
                if (playerChoice == DrawCommand.DRAW) {
                    blackJackGame.drawPlayerCard(playerName);
                }
                outputView.printCardStatusOfPlayer(playerName, convertCards(blackJackGame.getPlayerCards(playerName)));
            }
        }

        while (blackJackGame.isDealerDrawable()) {
            blackJackGame.drawDealerCard();
            outputView.printDealerCardDrawMessage();
        }

        outputView.printFinalStatusOfDealer(blackJackGame.getDealerScore(),
                convertCards(blackJackGame.getDealerCards()));
        outputView.printFinalStatusOfPlayers(convertPlayersCards(blackJackGame.getPlayersCards()),
                blackJackGame.getPlayersScores());
        outputView.printFinalDealerResult(convertDealerResults(blackJackGame.generatePlayersResult()));
        outputView.printFinalPlayersResult(convertPlayersResult(blackJackGame.generatePlayersResult()));
    }

    private DrawCommand inputPlayerChoice(final String playerName) {
        return repeatUntilNoException(
                () -> inputView.inputCommand(playerName), outputView::printError);
    }

    private List<String> inputPlayerNames() {
        return inputView.inputPlayerNames();
    }
}
