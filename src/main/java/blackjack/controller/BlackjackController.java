package blackjack.controller;

import blackjack.domain.machine.BlackjackGame;
import blackjack.domain.machine.GameResponse;
import blackjack.domain.machine.Hit;
import blackjack.domain.participant.Player;
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

    private List<GameResponse> toResponse(List<Player> players) {
        List<GameResponse> gameResponses = new ArrayList<>();
        for (Player player : players) {
            gameResponses.add(new GameResponse(player.getName(), player.getDeck()));
        }
        return gameResponses;
    }

    private void turnGuests(BlackjackGame blackjackGame) {
        while (blackjackGame.hasNextGuest()) {
            turnEachGuest(blackjackGame);
        }
    }

    private void turnEachGuest(BlackjackGame blackjackGame) {
        while (canGetMoreCard(blackjackGame)) {
            GameResponse gameResponse = blackjackGame.addCardToPlayer();
            OutputView.announcePresentCards(gameResponse);
        }
        blackjackGame.turnGuest();
    }

    private boolean canGetMoreCard(BlackjackGame blackjackGame) {
        if (blackjackGame.checkOverLimit()) {
            return false;
        }
        Player player = blackjackGame.getTurnPlayer();
        String userResponse = InputView.requestMoreCard(player.getName());
        return Hit.isYes(userResponse);
    }

    private void turnDealer(BlackjackGame blackjackGame) {
        announceDealerCanGetMoreCard(blackjackGame);
    }

    private void announceDealerCanGetMoreCard(BlackjackGame blackjackGame) {

        if (blackjackGame.canGetMoreCardToDealer()) {
            OutputView.announceDealerGetMoreCard();
            blackjackGame.addCardToDealer();
            return;
        }
        OutputView.announceDealerStopMoreCard();
    }

    private void printResult(BlackjackGame blackjackGame) {
        OutputView.announceResultCards(toResponse(blackjackGame.getPlayers()));
        OutputView.announceResultWinner(blackjackGame.calculateResult());
    }
}
