package controller;

import model.participant.Name;
import model.participant.Participants;
import view.GameSetupView;

public final class GameSetupController {
    private final GameSetupView gameSetupView = new GameSetupView();

    public String getInputNameGuide() {
        return gameSetupView.getInputNameGuide();
    }

    public String getBettingGuide(final Name playerName) {
        return gameSetupView.getBettingGuide(playerName);
    }

    public String getSetupResult(final Participants participants) {
        return gameSetupView.getSetupResult(participants);
    }
}
