package blackjack.controller;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.*;
import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.Deck;
import blackjack.domain.card.PlayingCardShuffleMachine;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.Results;
import blackjack.utils.InputValidator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void playGame() {
        BlackjackGame blackjackGame = new BlackjackGame(Deck.create(), receivePlayerNames());
        CardShuffleMachine playingCardShuffleMachine = new PlayingCardShuffleMachine();
        blackjackGame.initGames(playingCardShuffleMachine);

        Players players = blackjackGame.getPlayers();
        announceStartGame(blackjackGame, players);

        turnPlayers(blackjackGame, playingCardShuffleMachine);
        turnDealer(blackjackGame, playingCardShuffleMachine);
        announceResult(blackjackGame, players);
    }

    private List<String> receivePlayerNames() {
        try {
            return InputView.inputPlayerNames();
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
        OutputView.announcePresentCards(blackjackGame.getPlayersGameResponses());
    }

    private void turnPlayers(BlackjackGame blackjackGame, CardShuffleMachine playingCardShuffleMachine) {
        while (blackjackGame.isExistNextPlayer()) {
            hasHit(blackjackGame, playingCardShuffleMachine);
        }
    }

    private void hasHit(BlackjackGame blackjackGame, CardShuffleMachine playingCardShuffleMachine) {
        while (blackjackGame.isTurnGuest() && receiveHit(blackjackGame.getTurnPlayer().getName())) {
            blackjackGame.assignCard(blackjackGame.getTurnPlayer(), playingCardShuffleMachine);
            announcePresentCard(blackjackGame);
        }
        blackjackGame.nextTurn();
    }

    private boolean receiveHit(String name) {
        try {
            String answer = InputView.requestHit(name);
            return answer.equals(InputValidator.MORE_CARD);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return receiveHit(name);
    }

    private void turnDealer(BlackjackGame blackjackGame, CardShuffleMachine playingCardShuffleMachine) {
        if (blackjackGame.isTurnDealer(playingCardShuffleMachine)) {
            OutputView.announceHit(Dealer.MAX_POINT);
            return;
        }
        OutputView.announceHit(Dealer.EXCEED_POINT);
    }

    private void announcePresentCard(BlackjackGame blackjackGame) {
        OutputView.announcePresentCards(blackjackGame.getTurnPlayerGameResponse());
    }

    private void announceResult(BlackjackGame blackjackGame, Players players) {
        OutputView.announceResultCards(blackjackGame.getPlayersGameResponses());
        Results results = blackjackGame.calculateResult(players);
        OutputView.announceResultWinner(results);
    }
}
