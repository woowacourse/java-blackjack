package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.domain.RandomDeckGenerator;
import blackjack.domain.WinningStatus;
import blackjack.dto.CardResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.ViewRenderer;

import java.util.List;
import java.util.Map;

public class BlackJackController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        final BlackJackGame blackJackGame = initBlackJackGame();
        printInitialStatus(blackJackGame);
        playPlayerTurn(blackJackGame);
        playDealerTurn(blackJackGame);
        printCardResult(blackJackGame);
        printWinningResult(blackJackGame);
    }

    private BlackJackGame initBlackJackGame() {
        outputView.printPlayerNameRequestMessage();
        final List<String> playerNames = inputView.readPlayerNames();
        outputView.printLineBreak();
        return new BlackJackGame(playerNames, new RandomDeckGenerator());
    }

    private void printInitialStatus(final BlackJackGame blackJackGame) {
        outputView.printInitialStatus(ViewRenderer.renderStatus(blackJackGame.getInitialStatus()));
    }

    private void playPlayerTurn(final BlackJackGame blackJackGame) {
        final List<String> playerNames = blackJackGame.getPlayerNames();
        for (final String playerName : playerNames) {
            playFor(blackJackGame, playerName);
            outputView.printLineBreak();
        }
    }

    private void playFor(final BlackJackGame blackJackGame, final String playerName) {
        while (isContinuous(playerName, blackJackGame)) {
            blackJackGame.playPlayer(playerName);
            List<Card> userCards = blackJackGame.getStatus().get(playerName);
            outputView.printCards(playerName, ViewRenderer.renderCardsToString(userCards));
        }
    }

    private boolean isContinuous(final String playerName, BlackJackGame blackJackGame) {
        if (blackJackGame.isBlackJackScore(playerName) || blackJackGame.isBust(playerName)) {
            return false;
        }
        outputView.printDrawCardRequestMessage(playerName);
        return DrawInput.from(inputView.readDrawOrStay()).isDraw();
    }

    private void playDealerTurn(BlackJackGame blackJackGame) {
        int dealerDrawCount = blackJackGame.playDealerTurn();
        while (dealerDrawCount-- > 0) {
            outputView.printDealerDrawInfoMessage();
        }
        outputView.printLineBreak();
    }

    private void printCardResult(BlackJackGame blackJackGame) {
        final Map<String, CardResult> cardResult = blackJackGame.getCardResult();
        for (final String name : cardResult.keySet()) {
            final CardResult cardDto = cardResult.get(name);
            outputView.printCardResult(name, ViewRenderer.renderCardsToString(cardDto.getCards()), cardDto.getScore());
        }
        outputView.printLineBreak();
    }

    private void printWinningResult(BlackJackGame blackJackGame) {
        Map<String, WinningStatus> winningResult = blackJackGame.getWinningResult();
        outputView.printWinningResult(ViewRenderer.renderWinningResult(winningResult));
    }
}
