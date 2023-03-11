package blackjack;

import static blackjack.util.Repeater.repeatUntilNoException;

import blackjack.domain.card.ShuffledDeckFactory;
import blackjack.domain.service.BlackJackRule;
import blackjack.service.BlackJackGame;
import blackjack.view.DrawCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(final String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final BlackJackGame blackJackGame = repeatUntilNoException(
                () -> BlackJackGame.of(
                        inputView.inputPlayerNames(),
                        new ShuffledDeckFactory(),
                        new BlackJackRule()),
                inputView::printInputError);
        for (final String playerName : blackJackGame.getPlayerNames()) {
            blackJackGame.addPlayerMoney(playerName, inputPlayerMoney(playerName, inputView));
        }

        blackJackGame.distributeInitialCard();
        outputView.printInitialCards(
                blackJackGame.getDealerFirstCard(),
                blackJackGame.getPlayersCards());

        for (final String playerName : blackJackGame.getPlayerNames()) {
            DrawCommand playerChoice = DrawCommand.DRAW;
            while (blackJackGame.isPlayerDrawable(playerName) && playerChoice != DrawCommand.STAY) {
                playerChoice = inputPlayerChoice(playerName, inputView);
                if (playerChoice == DrawCommand.DRAW) {
                    blackJackGame.drawPlayerCard(playerName);
                }
                outputView.printCardStatusOfPlayer(playerName, blackJackGame.getPlayerCards(playerName));
            }
        }
        while (blackJackGame.isDealerDrawable()) {
            blackJackGame.drawDealerCard();
            outputView.printDealerCardDrawMessage();
        }

        outputView.printFinalStatusOfDealer(blackJackGame.getDealerScore(),
                blackJackGame.getDealerCards());
        outputView.printFinalStatusOfPlayers(blackJackGame.getPlayersCards(),
                blackJackGame.getPlayersScores());
        outputView.printFinalMoney(blackJackGame.calculatePlayersMoney());
    }

    private static DrawCommand inputPlayerChoice(final String playerName, final InputView inputView) {
        return repeatUntilNoException(
                () -> inputView.inputCommand(playerName), inputView::printInputError);
    }

    private static int inputPlayerMoney(final String playerName, final InputView inputView) {
        return repeatUntilNoException(
                () -> inputView.inputPlayerMoney(playerName), inputView::printInputError);
    }
}
