package blackjack.domain.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = initBlackJackGame();
        setupAllPlayersBetting(blackJackGame);
        OutputView.printInitialCardStatus(blackJackGame.getParticipantsDto());

        playUntilAllTurnsEnd(blackJackGame);
        OutputView.showDealerResult(blackJackGame.dealerDrawMoreCard());
        OutputView.showFinalCardsAndScore(blackJackGame.getFinalParticipantsDto());

        blackJackGame.calculateGameResults();
        OutputView.showDealerRevenue(blackJackGame.getDealer());
        OutputView.showPlayersResult(blackJackGame.getPlayers());
    }

    private void setupAllPlayersBetting(BlackJackGame blackJackGame) {
        while (blackJackGame.isAnyPlayerNotBetYet()) {
            setupBettingMoney(blackJackGame);
        }
    }

    private void playUntilAllTurnsEnd(BlackJackGame blackJackGame) {
        while(blackJackGame.isAnyPlayerTurnRemain()) {
            playTurn(blackJackGame);
        }
    }

    private void setupBettingMoney(BlackJackGame blackJackGame) {
        String name = blackJackGame.findPlayerNeedToBetMoney().getName();
        int money = askBettingMoney(name);
        try {
            blackJackGame.playerSetupBettingMoney(money);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            setupBettingMoney(blackJackGame);
        }
    }

    private int askBettingMoney(String name) {
        try {
            return InputView.askBettingMoney(name);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return askBettingMoney(name);
        }
    }

    private void playTurn(BlackJackGame blackJackGame) {
        String name = blackJackGame.playNameOnTurn();
        boolean decision = askMoreCard(name);
        List<Card> updatedCards = blackJackGame.playerDrawCardOnDecision(decision);
        if(decision) {
            OutputView.showDrawResult(name, updatedCards);
        }
    }

    private BlackJackGame initBlackJackGame() {
        try {
            return new BlackJackGame(InputView.askPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initBlackJackGame();
        }
    }

    private boolean askMoreCard(String name) {
        try {
            return InputView.askMoreCard(name);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return askMoreCard(name);
        }
    }
}
