package controller;

import domain.Participants;
import ui.InputView;
import ui.OutputView;

public class BlackjackController {

    private final Participants participants;

    public BlackjackController() {
        this.participants = new Participants(InputView.readPlayers());
    }

    public void run() {
        participants.initGame();
        OutputView.printCardsStatus(participants.getDealer(), participants.getPlayers());
        addCardPlayers();
        addCardToDealerIfPossible();
        OutputView.printCardsStatusWithScore(participants.getDealer(), participants.getPlayers());
        OutputView.printResults(participants.calculateAllResults());
    }

    private void addCardToDealerIfPossible() {
        if (participants.addCardToDealerIfPossible()) {
            OutputView.announceAddCardToDealer();
        }
    }

    private void addCardPlayers() {
        while (participants.isContinuable()) {
            addCardPlayer();
            participants.passPlayer();
        }
    }

    private void addCardPlayer() {
        String currentPlayerName = participants.getCurrentPlayerName();

        while (participants.isCurrentPlayerCanAdd() && InputView.readWhetherDrawCardOrNot(currentPlayerName)) {
            participants.addCardToCurrentPlayer();
            OutputView.printCardsStatusOfPlayer(currentPlayerName, participants.getCurrentPlayerCardHand());
        }

        OutputView.printCardsStatusOfPlayer(currentPlayerName, participants.getCurrentPlayerCardHand());
    }
}
