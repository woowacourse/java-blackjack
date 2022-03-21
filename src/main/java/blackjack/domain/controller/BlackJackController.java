package blackjack.domain.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = initBlackJackGame();
        OutputView.printInitialCardStatus(blackJackGame.getParticipantsDto());

        playUntilAllTurnsEnd(blackJackGame);
        OutputView.showDealerResult(blackJackGame.dealerDrawMoreCard());
        OutputView.showFinalCardsAndScore(blackJackGame.getFinalParticipantsDto());

        OutputView.showDealerRevenue(blackJackGame.calculateGameResults());
    }

    private List<Player> setupAllPlayersBetting(List<String> names) {
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            name = name.trim();
            players.add(new Player(name, askBettingMoney(name)));
        }
        return players;
    }

    private void playUntilAllTurnsEnd(BlackJackGame blackJackGame) {
        while(blackJackGame.isAnyPlayerTurnRemain()) {
            playTurn(blackJackGame);
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
            List<String> names = InputView.askPlayerNames();
            return new BlackJackGame(setupAllPlayersBetting(names));
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
