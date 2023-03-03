package domain.controller;

import domain.participant.Participants;
import domain.view.InputView;

import java.util.List;

public class GameController {

    private final InputView inputView;

    public GameController(final InputView inputView) {
        this.inputView = inputView;
    }

    public void start() {
        Participants participants = makeParticipants();
    }

    private Participants makeParticipants() {
        return inputView.getInputWithRetry(() -> {
            List<String> participantNames = inputView.getParticipantNames();
            return Participants.create(participantNames);
        });
    }
}
