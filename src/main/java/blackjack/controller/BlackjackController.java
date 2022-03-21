package blackjack.controller;

import blackjack.domain.machine.BlackjackGame;
import blackjack.domain.machine.GameResponse;
import blackjack.domain.machine.Hit;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void playGame() {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputPlayerNames());

        Map<Name, Double> bettingBox = betMoney(blackjackGame.getGuests());
        announceStartGame(blackjackGame);
        blackjackGame.initGame();
        OutputView.announcePresentCards(toResponse(blackjackGame.getPlayers()));

        decideGuestsToGetMoreCards(blackjackGame);
        decideDealerGetMoreCard(blackjackGame);

        printResult(blackjackGame, bettingBox);
    }

    private void announceStartGame(BlackjackGame blackjackGame) {
        List<String> playerNames = blackjackGame.getPlayerNames().stream()
                .map(Name::value)
                .collect(Collectors.toList());
        OutputView.announceStartGame(playerNames);
    }

    private Map<Name, Double> betMoney(List<Player> guests) {
        Map<Name, Double> bettingBox = new LinkedHashMap<>();
        for (Player guest : guests) {
            Name guestName = guest.getName();
            bettingBox.put(guestName, InputView.inputBettingMoney(guestName.value()));
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
        String userResponse = InputView.requestMoreCard(player.getName().value());
        return Hit.isYes(userResponse);
    }

    private void decideDealerGetMoreCard(BlackjackGame blackjackGame) {
        if (blackjackGame.canGetMoreCardToDealer()) {
            OutputView.announceDealerStopMoreCard();
            return;
        }
        OutputView.announceDealerGetMoreCard();
        blackjackGame.addCardToDealer();
    }

    private void printResult(BlackjackGame blackjackGame,
                             Map<Name, Double> bettingBox) {
        OutputView.announceResultCards(toResponse(blackjackGame.getPlayers()));
        OutputView.announceResultWinner(blackjackGame.calculateResult(bettingBox));
    }
}
