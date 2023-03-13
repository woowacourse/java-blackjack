package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {

    private static final String CARD_RECEIVE_INTENTION = "y";

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackJackGame blackJackGame;

    public BlackJackController(InputView inputView, OutputView outputView, BlackJackGame blackJackGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJackGame = blackJackGame;
    }

    public void run() {
        registerPlayers();

        betMoneyAllPlayer();

        passInitialCard();

        passExtraCardToPlayer();

        finishGame();
    }

    private void betMoneyAllPlayer() {
        List<String> allPlayerNames = blackJackGame.findAllPlayerNames();
        for (String playerName : allPlayerNames) {
            betMoney(playerName);
        }
    }

    private void betMoney(String playerName) {
        try {
            double inputMoney = inputView.readBettingMoney(playerName);
            blackJackGame.betMoney(playerName, inputMoney);
        } catch (IllegalArgumentException e) {
            outputView.showError(e.getMessage());
            betMoney(playerName);
        }
    }

    private void registerPlayers() {
        try {
            List<String> names = inputView.readNames();
            blackJackGame.registerPlayers(names);
        } catch (IllegalArgumentException e) {
            outputView.showError(e.getMessage());
            registerPlayers();
        }
    }

    private void passInitialCard() {
        blackJackGame.setUp();
        outputView.showInitStatus(blackJackGame.findAllPlayerNames());
        outputView.showDealerFirstCard(blackJackGame.findDealerFirstCard());
        outputView.showAllPlayerNameHand(blackJackGame.findAllPlayerNameHand());
    }

    private void passExtraCardToPlayer() {
        passExtraCardToAllPlayers();
        passExtraCardToDealer();
    }

    private void passExtraCardToAllPlayers() {
        List<String> playerNames = blackJackGame.findAllPlayerNames();
        playerNames.forEach(this::passExtraCardToPlayer);
    }

    private void passExtraCardToPlayer(String playerName) {
        while (isContinuePassPlayerCard(playerName)) {
            blackJackGame.passExtraCardToPlayer(playerName);
            outputView.showPlayerNameHand(blackJackGame.findPlayerNameHand(playerName));
        }
    }

    private boolean isContinuePassPlayerCard(String playerName) {
        if (blackJackGame.canPassCardToPlayer(playerName)) {
            String intention = inputView.readIntention(playerName);
            return hasIntention(intention);
        }
        return false;
    }

    private boolean hasIntention(String intention) {
        return intention.equals(CARD_RECEIVE_INTENTION);
    }

    private void passExtraCardToDealer() {
        while (blackJackGame.canPassCardToDealer()) {
            blackJackGame.passExtraCardToDealer();
            outputView.showDealerDrawPossible();
        }
        outputView.showDealerDrawImpossible();
    }

    private void finishGame() {
        outputView.showTotalScoreDealer(blackJackGame.findDealerHandScore());
        outputView.showAllPlayerNameHandScore(blackJackGame.findAllPlayerNameHandScore());
        outputView.showTotalResult(blackJackGame.findDealerPlayerResult());
    }

}
