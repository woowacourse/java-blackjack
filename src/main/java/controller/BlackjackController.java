package controller;

import domain.BlackjackAction;
import domain.BlackjackGame;
import domain.BlackjackGameResult;
import domain.DeckFactory;
import domain.Participant;
import domain.Participants;
import domain.PlayerNames;
import java.util.List;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame blackjackGame = createBlackjackGame();
        blackjackGame.handOutInitialCards();
        outputView.printInitialCards(blackjackGame.getParticipants());

        playPlayersTurn(blackjackGame);
        playDealerTurn(blackjackGame);

        printResults(blackjackGame);
    }

    private BlackjackGame createBlackjackGame() {
        Participants participants = retryOnInvalidUserInput(this::requestPlayers);
        return BlackjackGame.from(participants, DeckFactory.getShuffledDeck());
    }

    private Participants requestPlayers() {
        List<String> playerNamesUserInput = inputView.requestPlayerNames();
        PlayerNames playerNames = PlayerNames.from(playerNamesUserInput);

        return Participants.from(playerNames);
    }

    private void playPlayersTurn(BlackjackGame blackjackGame) {
        for (Participant player : blackjackGame.getPlayers()) {
            playPlayerTurn(blackjackGame, player);
        }
    }

    private void playPlayerTurn(BlackjackGame blackjackGame, Participant player) {
        BlackjackAction blackjackAction;

        do {
            blackjackAction = requestAction(player.getName());
            blackjackGame.playByAction(player, blackjackAction);
            outputView.printParticipantCards(player);
        } while (blackjackGame.isAbleToContinue(player, blackjackAction));

        if (player.isBusted()) {
            outputView.printBusted(player.getName());
        }
    }

    private BlackjackAction requestAction(String playerName) {
        return retryOnInvalidUserInput(() -> {
                    String commandValue = inputView.requestMoreCard(playerName);
                    return BlackjackAction.from(commandValue);
                }
        );
    }

    private void playDealerTurn(BlackjackGame blackjackGame) {
        Participant dealer = blackjackGame.getDealer();
        blackjackGame.handOutAdditionalCardToDealer();

        int hitCardCount = blackjackGame.getParticipantHitCardCount(dealer);
        outputView.printDealerHitCount(hitCardCount);
    }

    private void printResults(BlackjackGame blackjackGame) {
        BlackjackGameResult blackjackGameResult = BlackjackGameResult.from(blackjackGame);

        Participants participants = blackjackGame.getParticipants();
        outputView.printCardsWithScore(participants);
        outputView.printFinalResult(blackjackGame.getDealer(), blackjackGameResult);
    }

    private <T> T retryOnInvalidUserInput(Supplier<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return retryOnInvalidUserInput(request);
        }
    }
}
