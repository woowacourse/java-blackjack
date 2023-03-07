package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.RandomDeckGenerator;
import blackjack.domain.result.CardResult;
import blackjack.domain.result.WinningStatus;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.ViewRenderer;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class BlackJackController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        final BlackJackGame blackJackGame = initBlackJackGame();
        printFirstOpenCardGroups(blackJackGame);
        playPlayersTurn(blackJackGame);
        playDealerTurn(blackJackGame);
        printUserNameAndCardResults(blackJackGame);
        printUserWinningResults(blackJackGame);
    }

    private BlackJackGame initBlackJackGame() {
        try {
            outputView.printPlayerNameRequestMessage();
            final List<String> playerNames = inputView.readPlayerNames();
            return new BlackJackGame(playerNames, new RandomDeckGenerator());
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return initBlackJackGame();
        }
    }

    private void printFirstOpenCardGroups(final BlackJackGame blackJackGame) {
        outputView.printFirstOpenCardGroups(ViewRenderer.renderStatus(blackJackGame.getFirstOpenCardGroups()));
    }

    private void playPlayersTurn(final BlackJackGame blackJackGame) {
        final List<String> playerNames = blackJackGame.getPlayerNames();
        for (final String playerName : playerNames) {
            playFor(blackJackGame, playerName);
        }
    }

    private void playFor(final BlackJackGame blackJackGame, final String playerName) {
        while (repeat(this::isContinuous, playerName, blackJackGame).isDraw()) {
            blackJackGame.playPlayer(playerName);
            CardGroup userCards = blackJackGame.getStatus().get(playerName);
            outputView.printCards(playerName, ViewRenderer.renderCardsToString(userCards));
        }
    }

    public DrawInput repeat(final BiFunction<String, BlackJackGame, DrawInput> isContinuous,
                            final String playerName, final BlackJackGame blackJackGame) {
        try {
            return isContinuous.apply(playerName, blackJackGame);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return repeat(isContinuous, playerName, blackJackGame);
        }
    }

    private DrawInput isContinuous(final String playerName, final BlackJackGame blackJackGame) {
        if (blackJackGame.isBlackJackScore(playerName) || blackJackGame.isPlayerBust(playerName)) {
            return DrawInput.STAY;
        }
        outputView.printDrawCardRequestMessage(playerName);
        return DrawInput.from(inputView.readDrawOrStay());
    }

    private void playDealerTurn(BlackJackGame blackJackGame) {
        while (blackJackGame.shouldDealerDraw()) {
            outputView.printDealerDrawInfoMessage();
            blackJackGame.drawDealer();
        }
    }

    private void printUserNameAndCardResults(BlackJackGame blackJackGame) {
        final Map<String, CardResult> userNameAndCardResults = blackJackGame.getUserNameAndCardResults();
        final Map<String, String> renderedUserNameAndCardResults = ViewRenderer
                .renderUserNameAndCardResults(userNameAndCardResults);
        outputView.printUserNameAndCardResults(renderedUserNameAndCardResults);
    }

    private void printUserWinningResults(final BlackJackGame blackJackGame) {
        final Map<WinningStatus, Long> dealerWinningResult = blackJackGame.getDealerWinningResult();
        outputView.printDealerWinningResult(ViewRenderer.renderDealerWinningResult(dealerWinningResult));
        final Map<String, WinningStatus> playersWinningResult = blackJackGame.getPlayersWinningResults();
        outputView.printPlayersWinningResults(ViewRenderer.renderPlayersWinningResults(playersWinningResult));
    }
}
