package blackjack.controller;

import static blackjack.domain.Dealer.DEALER_LIMIT_POINT;
import static blackjack.domain.Guest.GUEST_LIMIT_POINT;

import blackjack.domain.BlackjackGame;
import blackjack.domain.GameResponse;
import blackjack.domain.Hit;
import blackjack.domain.Player;
import blackjack.domain.Results;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    public void playGame() {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputPlayerNames());

        OutputView.announceStartGame(blackjackGame.getPlayerNames());
        OutputView.announcePresentCards(toResponse(blackjackGame.getPlayers()));

        turnGuests(blackjackGame);
        turnDealer(blackjackGame);

        printResult(blackjackGame);
    }

    private void printResult(BlackjackGame blackjackGame) {
        List<Player> players = blackjackGame.getPlayers();
        OutputView.announceResultCards(toResponse(players));
        Results results = blackjackGame.calculateResult(players);
        OutputView.announceResultWinner(results);
    }

    private void turnDealer(BlackjackGame blackjackGame) {
        Player dealer = blackjackGame.getDealer();
        announceDealerCanGetMoreCard(blackjackGame, dealer);
    }

    private void turnGuests(BlackjackGame blackjackGame) {
        List<Player> guests = blackjackGame.getGuests();
        for (Player guest : guests) {
            turnEachGuest(blackjackGame, guest);
        }
    }

    private void turnEachGuest(BlackjackGame blackjackGame, Player player) {
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
        String userResponse = InputView.requestMoreCard(player.getName());
        return Hit.isYes(userResponse);
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
