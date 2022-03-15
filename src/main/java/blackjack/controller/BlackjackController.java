package blackjack.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.*;
import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.Deck;
import blackjack.domain.card.PlayingCardShuffleMachine;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.GameResponse;
import blackjack.domain.result.Results;
import blackjack.utils.InputValidator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private final CardShuffleMachine playingCardShuffleMachine = new PlayingCardShuffleMachine();

    public void playGame() {
        BlackjackGame blackjackGame = new BlackjackGame(Deck.create(), receivePlayerNames());
        blackjackGame.initGames(playingCardShuffleMachine);

        Players players = blackjackGame.getPlayers();
        announceStartGame(blackjackGame, players);

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

    private void announceStartGame(BlackjackGame blackjackGame, Players players) {
        OutputView.announceStartGame(players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList()));

        OutputView.announcePresentCards(blackjackGame.getGameResponse(players));
    }

    private void turnPlayers(BlackjackGame blackjackGame) {
        while (blackjackGame.isExistNextPlayer()) {
            hasHit(blackjackGame);
        }
    }

    private void hasHit(BlackjackGame blackjackGame) {
        while (blackjackGame.isTurnGuest() && receiveHit(blackjackGame.getTurnPlayer().getName())) {
            blackjackGame.assignCard(blackjackGame.getTurnPlayer(), playingCardShuffleMachine);
            announcePresentCard(blackjackGame.getTurnPlayer());
        }
        blackjackGame.nextTurn();
    }

    private boolean receiveHit(String name) {
        try {
            String answer = InputView.requestHit(name);
            InputValidator.inputBlank(answer);
            InputValidator.isAnswerFormat(answer);
            return answer.equals(InputValidator.MORE_CARD);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return receiveHit(name);
    }

    private void turnDealer(BlackjackGame blackjackGame) {
        if (blackjackGame.isTurnDealer(playingCardShuffleMachine)) {
            OutputView.announceHit(Dealer.MAX_POINT);
            return;
        }
        OutputView.announceHit(Dealer.EXCEED_POINT);
    }

    private void announcePresentCard(Player player) {
        List<GameResponse> gameResponses = new ArrayList<>();
        GameResponse gameResponse = new GameResponse(player.getName(), player.getDeck());
        gameResponses.add(gameResponse);
        OutputView.announcePresentCards(gameResponses);
    }

    private void announceResult(BlackjackGame blackjackGame, Players players) {
        OutputView.announceResultCards(blackjackGame.getGameResponse(players));
        Results results = blackjackGame.calculateResult(players);
        OutputView.announceResultWinner(results);
    }
}
