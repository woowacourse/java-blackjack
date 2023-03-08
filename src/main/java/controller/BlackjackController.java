package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame blackjackGame = createBlackjackGame();

        play(blackjackGame);
        printResult(blackjackGame);
    }

    private BlackjackGame createBlackjackGame() {
        return new BlackjackGame(createPlayers());
    }

    private Players createPlayers() {
        Names names = createNames();

        return Players.from(names.getNames().stream()
                .map(name -> Player.of(name, createBetAmount(name)))
                .collect(Collectors.toList()));
    }

    private Names createNames() {
        return retryOnInvalidUserInput(
                () -> Names.from(readPlayerNames())
        );
    }

    private List<String> readPlayerNames() {
        return inputView.requestPlayerNames();
    }

    private BettingMoney createBetAmount(Name name) {
        return retryOnInvalidUserInput(
                () -> BettingMoney.from(readBetAmount(name))
        );
    }

    private int readBetAmount(Name name) {
        return inputView.requestBettingMoney(name.getName());
    }

    private void play(BlackjackGame blackjackGame) {
        blackjackGame.giveInitialCards();
        outputView.printInitialCards(blackjackGame.getDealer(), blackjackGame.getPlayers());

        Players players = blackjackGame.getPlayers();
        for (Player player : players.getPlayers()) {
            requestMoreCard(blackjackGame, player);
        }

        blackjackGame.giveAdditionalCardToDealer();
        outputView.printDealerHitCount(blackjackGame.getDealerHitCardCount());
    }

    private void requestMoreCard(BlackjackGame blackjackGame, Player player) {
        while (!player.isBusted() && wantMoreCard(player)) {
            blackjackGame.giveCardTo(player);
            outputView.printPlayerCards(player);
        }

        printPlayerCurrentState(player);
    }

    private boolean wantMoreCard(Player player) {
        PlayerAction command = retryOnInvalidUserInput(
                () -> PlayerAction.from(inputView.requestMoreCard(player.getName()))
        );

        return isHitCommand(command);
    }

    private boolean isHitCommand(PlayerAction command) {
        return command == PlayerAction.HIT;
    }

    private void printPlayerCurrentState(Player player) {
        if (player.isBusted()) {
            outputView.printBusted(player.getName());
            return;
        }

        outputView.printPlayerCards(player);
    }

    private void printResult(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();

        blackjackGame.decideResult();
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
