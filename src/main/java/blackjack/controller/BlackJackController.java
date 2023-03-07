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

public class BlackJackController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        final BlackJackGame blackJackGame = initBlackJackGame();
        printFirstOpenCardGroups(blackJackGame);
        playPlayerTurn(blackJackGame);
        playDealerTurn(blackJackGame);
        printCardResult(blackJackGame);
        printWinningResult(blackJackGame);
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

    private void playPlayerTurn(final BlackJackGame blackJackGame) {
        final List<String> playerNames = blackJackGame.getPlayerNames();
        for (final String playerName : playerNames) {
            playFor(blackJackGame, playerName);
        }
    }

    private void playFor(final BlackJackGame blackJackGame, final String playerName) {
        while (isContinuous(playerName, blackJackGame).isDraw()) {
            blackJackGame.playPlayer(playerName);
            CardGroup userCards = blackJackGame.getStatus().get(playerName);
            outputView.printCards(playerName, ViewRenderer.renderCardsToString(userCards));
        }
    }

    //TODO : indent 줄이기 + 메서드 라인 줄이기
    private DrawInput isContinuous(final String playerName, final BlackJackGame blackJackGame) {
        try {
            if (blackJackGame.isBlackJackScore(playerName) || blackJackGame.isPlayerBust(playerName)) {
                return DrawInput.STAY;
            }
            outputView.printDrawCardRequestMessage(playerName);
            return DrawInput.from(inputView.readDrawOrStay());
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return isContinuous(playerName, blackJackGame);
        }
    }

    private void playDealerTurn(BlackJackGame blackJackGame) {
        while (blackJackGame.shouldDealerDraw()) {
            outputView.printDealerDrawInfoMessage();
            blackJackGame.drawDealer();
        }
    }

    //TODO: 해당 로직 view로 분리하게 변경
    private void printCardResult(BlackJackGame blackJackGame) {
        final Map<String, CardResult> cardResults = blackJackGame.getUserNameAndCardResults();
        for (final String name : cardResults.keySet()) {
            final CardResult cardResult = cardResults.get(name);
            outputView.printCardResult(name, ViewRenderer.renderCardsToString(cardResult.getCards()),
                    cardResult.getScore().getValue());
        }
    }

    private void printWinningResult(BlackJackGame blackJackGame) {
        Map<String, WinningStatus> winningResult = blackJackGame.getWinningResult();
        outputView.printWinningResult(ViewRenderer.renderWinningResult(winningResult));
    }
}
