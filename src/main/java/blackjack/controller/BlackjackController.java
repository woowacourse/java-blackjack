package blackjack.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private static final String DEALER = "딜러";
    private static final String REQUEST_MORE_CARD = "y";
    private static final int DEALER_POINT_LIMIT = 21;
    private static final int MAX_POINT_FOR_MORE_CARD = 16;
    private static final int EXCEED_POINT_FOR_MORE_CARD = 17;

    public void playGame() {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputPlayerNames());
        Players players = blackjackGame.initGames();

        printStartGame(players);

        turnPlayers(blackjackGame, players);
        turnDealer(blackjackGame, players);

        printResult(blackjackGame, players);
    }

    private void printStartGame(Players players) {
        OutputView.announceStartGame(players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList()));
        OutputView.announcePresentCards(toResponse(players));
    }

    private void printResult(BlackjackGame blackjackGame, Players players) {
        OutputView.announceResultCards(toResponse(players));
        Results results = blackjackGame.calculateResult(players);
        OutputView.announceResultWinner(results);
    }

    private void turnDealer(BlackjackGame blackjackGame, Players players) {
        Player dealer = players.getDealer();
        announceDealerCanGetMoreCard(blackjackGame, dealer);
    }

    private void turnPlayers(BlackjackGame blackjackGame, Players players) {
        for (Player player : players.getPlayers()) {
            turnEachPlayerIfGuest(blackjackGame, player);
        }
    }

    private void turnEachPlayerIfGuest(BlackjackGame blackjackGame, Player player) {
        if (player.isDealer(DEALER)) {
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
        if (player.isOverLimit(DEALER_POINT_LIMIT)) {
            return false;
        }
        return InputView.requestMoreCard(player.getName())
                .equals(REQUEST_MORE_CARD);
    }

    private void announceDealerCanGetMoreCard(BlackjackGame blackjackGame, Player dealer) {
        if (!dealer.isOverLimit(MAX_POINT_FOR_MORE_CARD)) {
            OutputView.announceGetMoreCard(MAX_POINT_FOR_MORE_CARD);
            blackjackGame.addCard(dealer);
            return;
        }
        OutputView.announceGetMoreCard(EXCEED_POINT_FOR_MORE_CARD);
    }

    private List<GameResponse> toResponse(Players players) {
        List<GameResponse> gameResponses = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            gameResponses.add(new GameResponse(player.getName(), player.getDeck()));
        }
        return gameResponses;
    }
}
