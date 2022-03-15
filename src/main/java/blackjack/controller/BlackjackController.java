package blackjack.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.*;
import blackjack.domain.card.PlayingPlayingCardShuffleMachine;
import blackjack.domain.card.PlayingCards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.GameResponse;
import blackjack.domain.result.Results;
import blackjack.utils.InputValidator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void playGame() {
        BlackjackGame blackjackGame = new BlackjackGame(new PlayingCards(new PlayingPlayingCardShuffleMachine()));
        blackjackGame.initGames(receivePlayerNames());

        Players players = blackjackGame.getBlackjackPlayers();
        announceStartGame(players);

        turnPlayers(blackjackGame);
        turnDealer(blackjackGame);
        announceResult(blackjackGame, players);
    }

    private List<String> receivePlayerNames() {
        try {
            List<String> names = InputView.inputPlayerNames();
            InputValidator.inputListBlank(names);
            InputValidator.hasDuplicateName(names);
            return names;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return receivePlayerNames();
        }
    }

    private void announceStartGame(Players players) {
        OutputView.announceStartGame(players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList()));

        OutputView.announcePresentCards(toResponse(players));
    }

    private void turnPlayers(BlackjackGame blackjackGame) {
        while (blackjackGame.isExistNextPlayer()) {
            hasMoreCard(blackjackGame);
        }
    }

    private void hasMoreCard(BlackjackGame blackjackGame) {
        while (blackjackGame.isTurnGuest() && receiveForGetCard(blackjackGame.getTurnPlayer().getName())) {
            blackjackGame.addCard(blackjackGame.getTurnPlayer());
            announcePresentCard(blackjackGame.getTurnPlayer());
        }
        blackjackGame.nextTurn();
    }

    public boolean receiveForGetCard(String name) {
        try {
            String answer = InputView.requestMoreCard(name);
            InputValidator.inputBlank(answer);
            InputValidator.isAnswerFormat(answer);
            return answer.equals(InputValidator.MORE_CARD);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return receiveForGetCard(name);
    }

    private void turnDealer(BlackjackGame blackjackGame) {
        if (blackjackGame.turnDealer()) {
            OutputView.announceGetMoreCard(Dealer.MAX_POINT);
            return;
        }
        OutputView.announceGetMoreCard(Dealer.EXCEED_POINT);
    }

    private List<GameResponse> toResponse(Players players) {
        List<GameResponse> gameResponses = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            gameResponses.add(new GameResponse(player.getName(), player.getDeck()));
        }
        return gameResponses;
    }

    public void announcePresentCard(Player player) {
        List<GameResponse> gameResponses = new ArrayList<>();
        GameResponse gameResponse = new GameResponse(player.getName(), player.getDeck());
        gameResponses.add(gameResponse);
        OutputView.announcePresentCards(gameResponses);
    }

    private void announceResult(BlackjackGame blackjackGame, Players players) {
        OutputView.announceResultCards(toResponse(players));
        Results results = blackjackGame.calculateResult(players);
        OutputView.announceResultWinner(results);
    }
}
