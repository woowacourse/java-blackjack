package controller;

import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        Players players =  createPlayers();
        Participants participants = createParticipants(players);
    }

    private Players createPlayers() {
        String playerNames = inputView.inputPlayerName();
        return Players.of(playerNames);
    }

    private Participants createParticipants(Players players) {
        Participants participants = new Participants(new Dealer(), players);
        participants.initialTurn();
        outputView.showInitialTurnStatus(participants);
        return participants;
    }
}
