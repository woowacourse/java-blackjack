package blackjack.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.*;
import blackjack.utils.InputValidator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void playGame() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.initGames(receivePlayerNames());

        Players players = blackjackGame.getBlackjackPlayers();
        announceStartGame(players);

        if (!blackjackGame.isNotDealerBlackJack()) {
            blackjackGame.turnPlayers(this);
            blackjackGame.turnDealer(this);
        }

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

    public boolean receiveForGetMoreCard(String name) {
        try {
            String answer = InputView.requestMoreCard(name);
            InputValidator.inputBlank(answer);
            InputValidator.isAnswerFormat(answer);
            return answer.equals(InputValidator.MORE_CARD);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return receiveForGetMoreCard(name);
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

    public void announceDealerCanGetMoreCard() {
        OutputView.announceGetMoreCard(Dealer.MAX_POINT);
    }

    public void announceDealerCantGetMoreCard() {
        OutputView.announceGetMoreCard(Dealer.EXCEED_POINT);
    }

    private void announceResult(BlackjackGame blackjackGame, Players players) {
        OutputView.announceResultCards(toResponse(players));
        Results results = blackjackGame.calculateResult(players);
        OutputView.announceResultWinner(results);
    }
}
