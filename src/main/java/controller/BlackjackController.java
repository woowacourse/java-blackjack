package controller;

import domain.BlackjackAction;
import domain.BlackjackGame;
import domain.BlackjackGameResult;
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
        return BlackjackGame.from(participants);
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
        while (player.isAbleToReceiveCard()
                && requestAction(player) == BlackjackAction.HIT) {
            blackjackGame.handOutCardTo(player);
            outputView.printPlayerCards(player);
        }

        printPlayerCurrentState(player);
    }

    private BlackjackAction requestAction(Participant player) {
        return retryOnInvalidUserInput(() -> requestMoreCardTo(player));
    }

    private BlackjackAction requestMoreCardTo(Participant player) {
        String playerName = player.getName();
        String userInputCommand = inputView.requestMoreCard(playerName);

        return BlackjackAction.from(userInputCommand);
    }

    private void playDealerTurn(BlackjackGame blackjackGame) {
        Participant dealer = blackjackGame.getDealer();
        blackjackGame.handOutAdditionalCardToDealer();

        outputView.printDealerHitCount(dealer.getCards().size() - 2); //TODO 분리
    }

    private void printPlayerCurrentState(Participant player) {
        if (player.isBusted()) {
            outputView.printBusted(player.getName());
            return;
        }

        outputView.printPlayerCards(player);
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
