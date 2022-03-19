package blackjack.controller;

import blackjack.domain.machine.BlackjackGame;
import blackjack.domain.machine.GameResponse;
import blackjack.domain.machine.Hit;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    public void playGame() {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputPlayerNames());

        Map<Player, Double> bettingBox = betMoney(blackjackGame.getGuest());
        OutputView.announceStartGame(blackjackGame.getPlayerNames());
        blackjackGame.initGame();
        OutputView.announcePresentCards(toResponse(blackjackGame.getPlayers()));

        decideGuestsToGetMoreCards(blackjackGame);
        decideDealerGetMoreCard(blackjackGame);

        printResult(blackjackGame);
    }

    private Map<Player, Double> betMoney(List<Player> players) {
        Map<Player, Double> bettingBox = new LinkedHashMap<>();
        for (Player player : players) {
            bettingBox.put(player, InputView.inputBettingMoney(player.getName()));
        }
        return bettingBox;
    }


    private List<GameResponse> toResponse(List<Player> players) {
        List<GameResponse> gameResponses = new ArrayList<>();
        for (Player player : players) {
            gameResponses.add(new GameResponse(player.getName(), player.getDeck()));
        }
        return gameResponses;
    }

    private void decideGuestsToGetMoreCards(BlackjackGame blackjackGame) {
        while (blackjackGame.hasNextGuest()) {
            decideGuestToGetMoreCards(blackjackGame);
        }
    }

    private void decideGuestToGetMoreCards(BlackjackGame blackjackGame) {
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

    private void decideDealerGetMoreCard(BlackjackGame blackjackGame) {
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
