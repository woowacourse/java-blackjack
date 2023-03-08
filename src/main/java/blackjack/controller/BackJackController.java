package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.generator.RandomDeckGenerator;
import blackjack.dto.CardAndScoreResult;
import blackjack.dto.FinalResult;
import blackjack.dto.HoldingCards;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BackJackController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        BlackJackGame blackJackGame = initBlackJackGame();
        printInitialStatus(blackJackGame);
        playPlayerTurn(blackJackGame);
        playDealerTurn(blackJackGame);
        printCardResult(blackJackGame);
        printGameResult(blackJackGame);
    }

    private BlackJackGame initBlackJackGame() {
        outputView.printPlayerNameRequestMessage();
        final List<String> names = inputView.readPlayerNames();
        outputView.printLineBreak();
        return new BlackJackGame(names, new RandomDeckGenerator());
    }

    private void printInitialStatus(BlackJackGame blackJackGame) {
        outputView.printInitialHoldingCards(blackJackGame.getInitialHoldingCards());
        outputView.printLineBreak();
    }

    private void playPlayerTurn(BlackJackGame blackJackGame) {
        final List<String> playerNames = blackJackGame.getPlayerNames();
        for (final String name : playerNames) {
            playFor(blackJackGame, name);
            outputView.printLineBreak();
        }
    }

    private void playFor(BlackJackGame blackJackGame, String name) {
        while (isContinuous(name, blackJackGame)) {
            blackJackGame.playPlayer(name);
            final HoldingCards handholdingCards = blackJackGame.getHandholdingCards(name);
            outputView.printCards(handholdingCards);
        }
    }

    private boolean isContinuous(String name, BlackJackGame blackJackGame) {
        if (blackJackGame.isPossibleToDraw(name)) {
            outputView.printDrawCardRequestMessage(name);
            return DrawInput.from(inputView.readDrawOrStay()).isDraw();
        }
        return false;
    }

    private void playDealerTurn(BlackJackGame blackJackGame) {
        int dealerDrawCount = blackJackGame.playDealerTurn();
        while (dealerDrawCount-- > 0) {
            outputView.printDealerDrawInfoMessage();
        }
        outputView.printLineBreak();
    }

    private void printCardResult(BlackJackGame blackJackGame) {
        final List<CardAndScoreResult> cardAndScoreResult = blackJackGame.getCardAndScoreResult();
        outputView.printCarAndScoreResult(cardAndScoreResult);
        outputView.printLineBreak();
    }

    private void printGameResult(BlackJackGame blackJackGame) {
        final List<FinalResult> finalResults = blackJackGame.getFinalResults();
        outputView.printFinalResult(finalResults);
    }
}
