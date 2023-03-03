package controller;

import domain.BlackjackGame;
import domain.Command;
import domain.Dealer;
import domain.Player;
import domain.PlayerName;
import domain.Players;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

// TODO: 2023/03/02 View 리팩토링
// TODO: 2023/03/02 코드 스타일/포맷 맞추기
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
        result(blackjackGame);
    }

    private BlackjackGame createBlackjackGame() {
        BlackjackGame blackjackGame = new BlackjackGame(createPlayers());
        blackjackGame.giveInitCards();
        outputView.printInitCards(blackjackGame.getDealer(), blackjackGame.getPlayers());
        return blackjackGame;
    }

    private void play(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();
        for (Player player : players.getPlayers()) {
            requestMoreCard(blackjackGame, player);
        }

        Dealer dealer = blackjackGame.getDealer();
        blackjackGame.giveAdditionalCardToDealer();
        outputView.printDealerHitCount(dealer.getHitCardCount());
    }

    private void result(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();

        blackjackGame.result();
        Dealer dealer = blackjackGame.getDealer();
        outputView.printCardsWithScore(dealer, players);
        outputView.printFinalResult(dealer);
    }


    private Players createPlayers() {
        return retryOnInvalidUserInput(
                () -> new Players(readNames().stream()
                .map(PlayerName::new)
                .map(Player::new)
                .collect(Collectors.toList()))
        );
    }

    private List<String> readNames() {
        return inputView.requestPlayerNames();
    }

    private void requestMoreCard(BlackjackGame blackjackGame, Player player) {
        while (!player.isBusted() && isHitCommand(player.getName())) {
            blackjackGame.giveCardTo(player);
            outputView.printPlayerCards(player);
        }

        printPlayerCurrentState(player);
    }

    private boolean isHitCommand(String name) {
        Command command = retryOnInvalidUserInput(
                () -> Command.from(inputView.requestMoreCard(name))
        );

        return command == Command.HIT;
    }

    private void printPlayerCurrentState(Player player) {
        if (player.isBusted()) {
            outputView.printBusted(player.getName());
            return;
        }

        outputView.printPlayerCards(player);
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
