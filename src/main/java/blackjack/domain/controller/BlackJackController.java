package blackjack.domain.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.domain.GameMachine;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = initBlackJackGame();
        OutputView.printInitialCardStatus(blackJackGame.getParticipantsDto());

        while(blackJackGame.isAnyPlayerTurnRemain()) {
            playTurn(blackJackGame);
        }
        OutputView.showDealerResult(blackJackGame.dealerDrawMoreCard());
        OutputView.showFinalCardsAndScore(blackJackGame.getFinalParticipantsDto());

        OutputView.showGameResults(blackJackGame.getGameResultsDtos());
    }

    private void playTurn(BlackJackGame blackJackGame) {
        String name = blackJackGame.playNameOnTurn();
        boolean decision = askMoreCard(name);
        List<Card> updatedCard = blackJackGame.playerDrawCardOnDecision(decision);
        if(decision) {
            OutputView.showDrawResult(name, updatedCard);
        }
    }

    private BlackJackGame initBlackJackGame() {
        try {
            return GameMachine.createBlackJackGame(InputView.askPlayerNames());
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
