package controller;

import domain.BlackjackAction;
import domain.BlackjackGame;
import domain.Dealer;
import domain.Player;
import domain.PlayerNames;
import domain.Players;
import java.util.List;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame blackjackGame = createBlackjackGame();
        blackjackGame.handOutInitialCards();
        outputView.printInitialCards(blackjackGame.getDealer(), blackjackGame.getPlayers());

        playPlayersTurn(blackjackGame);
        playDealerTurn(blackjackGame);

        printResults(blackjackGame);
    }

    private BlackjackGame createBlackjackGame() {
        Players players = retryOnInvalidUserInput(this::requestPlayers);
        return new BlackjackGame(players);
    }

    private Players requestPlayers() {
        List<String> playerNamesUserInput = inputView.requestPlayerNames();
        PlayerNames playerNames = PlayerNames.from(playerNamesUserInput);

        return Players.from(playerNames);
    }

    private void playPlayersTurn(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();
        for (Player player : players.getPlayers()) {
            playPlayerTurn(blackjackGame, player);
        }
    }

    private void playPlayerTurn(BlackjackGame blackjackGame, Player player) {

        while (!player.isBusted()
                && requestAction(player) == BlackjackAction.HIT) {
            blackjackGame.handOutCardTo(player);
            outputView.printPlayerCards(player);
        }

        printPlayerCurrentState(player);
    }

    private BlackjackAction requestAction(Player player) {
        return retryOnInvalidUserInput(() -> requestMoreCardTo(player));
    }

    private BlackjackAction requestMoreCardTo(Player player) {
        String playerName = player.getName();
        String userInputCommand = inputView.requestMoreCard(playerName);

        return BlackjackAction.from(userInputCommand);
    }

    private void playDealerTurn(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        blackjackGame.handOutAdditionalCardToDealer();
        outputView.printDealerHitCount(dealer.getHitCardCount());
    }

    private void printPlayerCurrentState(Player player) {
        if (player.isBusted()) {
            outputView.printBusted(player.getName());
            return;
        }

        outputView.printPlayerCards(player);
    }

    private void printResults(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();

        blackjackGame.decideBlackjackGameResults();
        Dealer dealer = blackjackGame.getDealer();
        outputView.printCardsWithScore(dealer, players);
        outputView.printFinalResult(dealer);
    }

    private <T> T retryOnInvalidUserInput(Supplier<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return retryOnInvalidUserInput(request);
        }
    }
}
