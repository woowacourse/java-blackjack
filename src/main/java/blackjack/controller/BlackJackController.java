package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.generator.RandomDeckGenerator;
import blackjack.domain.user.Dealer;
import blackjack.dto.CardAndScoreResult;
import blackjack.dto.HoldingCards;
import blackjack.dto.ProfitResult;
import blackjack.util.RepeatValidator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        BlackJackGame blackJackGame = initBlackJackGame();
        bettingPlayers(blackJackGame);
        printInitialStatus(blackJackGame);
        playPlayerTurn(blackJackGame);
        playDealerTurn(blackJackGame);
        blackJackGame.judgeResults();
        printCardResult(blackJackGame);
        printProfit(blackJackGame);
    }

    private BlackJackGame initBlackJackGame() {
        outputView.printPlayerNameRequestMessage();
        return RepeatValidator.readUntilValidate(() -> {
            final List<String> names = inputView.readPlayerNames();
            outputView.printLineBreak();
            return new BlackJackGame(names, new RandomDeckGenerator());
        });
    }

    private void bettingPlayers(BlackJackGame blackJackGame) {
        final List<String> playerNames = blackJackGame.getPlayerNames();

        for (String name : playerNames) {
            RepeatValidator.runUntilValidate(() -> bettingPlayer(blackJackGame, name));
        }
    }

    private void bettingPlayer(final BlackJackGame blackJackGame, final String name) {
        outputView.printPlayerBettingAmountRequestMessage(name);
        final int amount = inputView.readBettingMoneyAmount();
        blackJackGame.bet(name, amount);
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
            return RepeatValidator.readUntilValidate(() -> DrawInput.from(inputView.readDrawOrStay()).isDraw());
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
        outputView.printCardAndScoreResult(cardAndScoreResult);
        outputView.printLineBreak();
    }

    private void printProfit(final BlackJackGame blackJackGame) {
        final List<ProfitResult> profitResults = new ArrayList<>();
        profitResults.add(new ProfitResult(Dealer.DEALER_NAME_CODE, blackJackGame.getDealerProfitAmount()));

        for (String name : blackJackGame.getPlayerNames()) {
            profitResults.add(new ProfitResult(name, blackJackGame.getPlayerProfitAmount(name)));
        }
        outputView.printProfitResult(profitResults);
    }
}
