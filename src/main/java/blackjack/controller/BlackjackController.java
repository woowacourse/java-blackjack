package blackjack.controller;

import static blackjack.domain.Dealer.DEALER_LIMIT_POINT;
import static blackjack.domain.Guest.GUEST_LIMIT_POINT;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Dealer;
import blackjack.domain.GameResponse;
import blackjack.domain.Player;
import blackjack.domain.Results;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    public void playGame() {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputPlayerNames());
        List<Player> players = blackjackGame.initGames();

        OutputView.announceStartGame(players.stream().map(Player::getName).collect(Collectors.toList()));
        OutputView.announcePresentCards(toResponse(players));

        turnPlayers(blackjackGame, players);
        turnDealer(blackjackGame, players);

        printResult(blackjackGame, players);
    }

    private void printResult(BlackjackGame blackjackGame, List<Player> players) {
        OutputView.announceResultCards(toResponse(players));
        Results results = blackjackGame.calculateResult(players);
        OutputView.announceResultWinner(results);
    }

    private void turnDealer(BlackjackGame blackjackGame, List<Player> players) {
        Player dealer = players.get(0);
        announceDealerCanGetMoreCard(blackjackGame, dealer);
    }

    private void turnPlayers(BlackjackGame blackjackGame, List<Player> players) {
        for (Player player : players) {
            turnEachPlayerIfGuest(blackjackGame, player);
        }
    }

    private void turnEachPlayerIfGuest(BlackjackGame blackjackGame, Player player) {
        if (player.getClass().equals(Dealer.class)) {
            return;
        }
        while (checkGetMoreCard(player)) {
            blackjackGame.addCard(player);
            List<GameResponse> gameResponses = new ArrayList<>();
            GameResponse gameResponse = new GameResponse(player.getName(), player.getDeck());
            gameResponses.add(gameResponse);
            OutputView.announcePresentCards(gameResponses);
        }
    }

    private boolean checkGetMoreCard(Player player) {
        if (player.isOverLimit(GUEST_LIMIT_POINT)) {
            return false;
        }
        return InputView.requestMoreCard(player.getName()).equals("y");
    }

    private void announceDealerCanGetMoreCard(BlackjackGame blackjackGame, Player dealer) {
        if (!dealer.isOverLimit(DEALER_LIMIT_POINT)) {
            OutputView.announceDealerGetMoreCard();
            blackjackGame.addCard(dealer);
            return;
        }
        OutputView.announceDealerStopMoreCard();
    }

    private List<GameResponse> toResponse(List<Player> players) {
        List<GameResponse> gameResponses = new ArrayList<>();
        for (Player player : players) {
            gameResponses.add(new GameResponse(player.getName(), player.getDeck()));
        }
        return gameResponses;
    }
}
